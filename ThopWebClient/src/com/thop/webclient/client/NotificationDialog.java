package com.thop.webclient.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NotificationDialog {

	//================================================================================
	// Dialog box for notifications
	//================================================================================
	public DialogBox displayNotificationDialog(String title, String content) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText(title);
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		final Label contentText = new Label(content);
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(contentText);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		return dialogBox;
	}

}
