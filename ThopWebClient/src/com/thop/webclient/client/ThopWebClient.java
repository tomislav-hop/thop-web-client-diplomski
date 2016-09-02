package com.thop.webclient.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.thop.webclient.client.clientObjects.Order;

public class ThopWebClient implements EntryPoint {
	private final ServiceAsync greetingService = GWT.create(Service.class);
	private RootPanel rootPanel;
	private RootPanel errorPanel;
	private TextBox nameField;
	private TextBox passwordField;
	private Button sendButton;
	private FlexTable loginTable;
	private FlexTable mainMenuTable;
	private PushButton btnAddOrder;
	private PushButton btnViewOrderHistory;
	private PushButton btnBakeTime;
	private CellTable<Order> orderTable;

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
		loginTable = new FlexTable();
		loginTable.setWidget(0, 0, lblNameField);
		loginTable.setWidget(0, 1, nameField);
		loginTable.setWidget(1, 0, lblPassword);
		loginTable.setWidget(1, 1, passwordField);
		loginTable.setWidget(2, 0, sendButton);
		loginTable.getFlexCellFormatter().setColSpan(2, 0, 2);
		rootPanel.add(loginTable);

		//================================================================================
		// Main menu
		//================================================================================
		btnAddOrder = new PushButton(new Image(""));
		btnAddOrder.setText("Add Order");
		btnViewOrderHistory = new PushButton(new Image(""));
		btnViewOrderHistory.setText("View Order History");
		btnBakeTime = new PushButton(new Image(""));
		btnBakeTime.setText("Bake time");
		mainMenuTable = new FlexTable();
		mainMenuTable.setWidget(0, 0, btnAddOrder);
		mainMenuTable.setWidget(0, 1, btnViewOrderHistory);
		mainMenuTable.setWidget(0, 2, btnBakeTime);

		//================================================================================
		// Label for displaying errors
		//================================================================================
		final Label errorLabel = new Label();
		sendButton.addStyleName("sendButton");
		errorPanel.add(errorLabel);

		//================================================================================
		// Orders table
		//================================================================================
		List<Order> orderList = new ArrayList<>();
		Order o = new Order(1, "sada", "sadfasd", "sadasd", "sadasd", 2, 2);
		Order o2 = new Order(1, "sada", "sadfasd", "sadasd", "sadasd", 2, 2);
		Order o3 = new Order(1, "sada", "sadfasd", "sadasd", "sadasd", 2, 2);
		Order o4 = new Order(1, "sada", "sadfasd", "sadasd", "sadasd", 2, 2);
		orderList.add(o);
		orderList.add(o2);
		orderList.add(o3);
		orderList.add(o4);
		
		orderTable = new CellTable<Order>();
		orderTable.setWidth("100%");
		orderTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<Order> nameColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getAdditionalNotes();
			}
		};
		orderTable.addColumn(nameColumn, "Additional notes");

		TextColumn<Order> adressColumn = new TextColumn<Order>() {
			@Override
			public String getValue(Order object) {
				return object.getOrderAdress();
			}
		};
		orderTable.addColumn(adressColumn, "Adress");

		  // Add a selection model to handle user selection.
	    final SingleSelectionModel<Order> selectionModel = new SingleSelectionModel<Order>();
	    orderTable.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        Order selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          //Window.alert("You selected: " + selected.name);
	        }
	      }
	    });

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    orderTable.setRowCount(orderList.size(), true);

	    // Push the data into the widget.
	    orderTable.setRowData(0, orderList);

		
		rootPanel.add(orderTable);

		//================================================================================
		// Login click handler
		//================================================================================
		class LoginHandler implements ClickHandler {
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
						//NotificationDialog nd = new NotificationDialog();
						//nd.displayNotificationDialog("Test", result).show();
						//rootPanel.remove(0);
						rootPanel.add(mainMenuTable);
					}
				});
			}
		}

		//================================================================================
		// Add Order click handler
		//================================================================================
		class AddOrderHandler implements ClickHandler {
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

					}
				});
			}
		}

		//================================================================================
		// View History click handler
		//================================================================================
		class ViewOrderHistoryHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				login();
			}

			private void login() {
				String username = nameField.getText();
				String password = passwordField.getText();
				greetingService.login(username, password, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {

					}

					public void onSuccess(String result) {

					}
				});
			}
		}
		//================================================================================
		// Bake time click handler
		//================================================================================
		class BakeTimeHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				login();
			}

			private void login() {
				String username = nameField.getText();
				String password = passwordField.getText();
				greetingService.login(username, password, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {

					}

					public void onSuccess(String result) {

					}
				});
			}
		}

		//================================================================================
		// Add clickhandlers to buttons
		//================================================================================
		LoginHandler loginHandler = new LoginHandler();
		AddOrderHandler addOrderHandler = new AddOrderHandler();
		ViewOrderHistoryHandler viewOrderHistoryHandler = new ViewOrderHistoryHandler();
		BakeTimeHandler bakeTimeHandler = new BakeTimeHandler();
		sendButton.addClickHandler(loginHandler);
		btnAddOrder.addClickHandler(addOrderHandler);
		btnViewOrderHistory.addClickHandler(viewOrderHistoryHandler);
		btnBakeTime.addClickHandler(bakeTimeHandler);

	}
}
