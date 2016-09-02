package com.thop.webclient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ThopWebClient implements EntryPoint {
	private final ServiceAsync greetingService = GWT.create(Service.class);
	private RootPanel rootPanel;
	private RootPanel errorPanel;
	private TextBox nameField;
	private TextBox passwordField;
	private Button sendButton;

	public void onModuleLoad() {

		//================================================================================
		// Main containers
		//================================================================================
		rootPanel = RootPanel.get("mainContainer");
		errorPanel = RootPanel.get("errorLabelContainer");

		//================================================================================
		// Login
		//================================================================================
		final Label lblNameField = new Label("Username:");
		lblNameField.setWidth("100px");
		nameField = new TextBox();
		nameField.setWidth("200px");
		nameField.setFocus(true);
		final Label lblPassword = new Label("Password:");
		lblPassword.setWidth("100px");
		passwordField = new TextBox();
		passwordField.setWidth("200px");
		sendButton = new Button("Login");
		sendButton.setWidth("316px");
		FlexTable t = new FlexTable();
		t.setWidget(0, 0, lblNameField);
		t.setWidget(0, 1, nameField);
		t.setWidget(1, 0, lblPassword);
		t.setWidget(1, 1, passwordField);
		t.setWidget(2, 0, sendButton);
		t.getFlexCellFormatter().setColSpan(2, 0, 2);
		rootPanel.add(t);

		//================================================================================
		// Label for displaying errors
		//================================================================================
		final Label errorLabel = new Label();
		sendButton.addStyleName("sendButton");
		errorPanel.add(errorLabel);

		//================================================================================
		// Login click handler
		//================================================================================
		class MyHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				login();
				rootPanel.remove(0);
			}

			private void login() {
				String username = nameField.getText();
				String password = passwordField.getText();
				greetingService.login(username, password, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {

					}

					public void onSuccess(String result) {
						NotificationDialog nd = new NotificationDialog();
						nd.displayNotificationDialog("Test", result).show();
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	}
}
