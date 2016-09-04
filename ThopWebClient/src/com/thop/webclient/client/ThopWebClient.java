package com.thop.webclient.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.thop.webclient.client.clientObjects.Item;
import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import com.thop.webclient.client.clientObjects.Package;
import com.thop.webclient.client.clientObjects.ReturnLists;
import com.thop.webclient.client.clientObjects.Status;

public class ThopWebClient implements EntryPoint {
	public static final ServiceAsync SERVICE = GWT.create(Service.class);
	private RootPanel rootPanel;
	private RootPanel bottomPanel;
	private TextBox nameField;
	private TextBox passwordField;
	private Button sendButton;
	private FlexTable loginTable;
	private FlexTable addOrderTable;
	private FlexTable orderItemButtonTable;
	private PushButton btnAddItem;
	private PushButton btnEditItem;
	private PushButton btnRemoveItem;
	private PushButton btnSendOrder;
	private CellTable<Order> orderTable;
	private int userId;
	private List<Order> orderList;
	private List<Status> statusList;
	private List<Item> itemList;
	private ListBox statusListBox;
	private ListBox itemListBox;
	private ListBox packageListBox;
	private List<Package> packageList;
	private TextBox orderNameField;
	private TextBox orderAdressField;
	private DatePicker orderDatePicker;
	private TextArea additionalNotes;
	private CellTable<OrderItems> orderItemsTable;
	private List<OrderItems> orderItemList = new ArrayList<OrderItems>();
	private List<OrderItems> historyOrderItemList = new ArrayList<OrderItems>();
	private ListBox timeListBox;
	private ListBox deliveryTimeListBox;
	private ListBox deadlineDateTimeListBox;
	private OrderItems newOrderItem;
	private Order order;
	private boolean firstTimeHistory = true;
	private boolean firstTimeAddOrder = true;
	private MenuBar menu;
	private OrderItems selectedItem;

	public void onModuleLoad() {

		//================================================================================
		// Main containers
		//================================================================================
		rootPanel = RootPanel.get("mainContainer");
		bottomPanel = RootPanel.get("bottomContainer");

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
		sendButton.addStyleName("sendButton");

		//TODO: Remove
		nameField.setValue("test");
		passwordField.setValue("test");

		//================================================================================
		// Main menu
		//================================================================================
		Command addOrderCommand = new Command() {
			public void execute() {
				cleanUI();
				displayAddOrder();
			}
		};

		Command orderHistoryCommand = new Command() {
			public void execute() {
				cleanUI();
				getOrderHistoryForUser();
			}
		};

		Command bakeTimeCommand = new Command() {
			public void execute() {
				cleanUI();
			}
		};

		menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("950px");
		menu.setAnimationEnabled(true);
		MenuItem m1 = new MenuItem("Add Order", addOrderCommand);
		MenuItem m2 = new MenuItem("Order History", orderHistoryCommand);
		MenuItem m3 = new MenuItem("Bake Time", bakeTimeCommand);
		menu.addItem(m1);
		menu.addItem(m2);
		menu.addItem(m3);

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
				SERVICE.login(username, password, new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						Window.alert("Login method has failed");
					}

					public void onSuccess(Integer result) {
						if (result != -1) {
							fillLists();
							rootPanel.add(menu);
							userId = result;
						} else {
							rootPanel.add(loginTable);
							Window.alert("Invalid username or password");
						}
					}
				});
			}
		}
		LoginHandler loginHandler = new LoginHandler();
		sendButton.addClickHandler(loginHandler);

	}

	//================================================================================
	// Orders table
	//================================================================================
	private void getOrderHistoryForUser() {
		SERVICE.getOrderList(userId, new AsyncCallback<List<Order>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Method 'getOrderList' has failed.");
			}

			@Override
			public void onSuccess(List<Order> result) {
				orderList = result;
				orderTable = new CellTable<Order>();
				orderTable.setWidth("100%");
				orderTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				TextColumn<Order> idOrderColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return String.valueOf(object.getOrderId());
					}
				};
				orderTable.addColumn(idOrderColumn, "Order ID");
				TextColumn<Order> orderedByColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return object.getOrderOrdered();
					}
				};
				orderTable.addColumn(orderedByColumn, "Ordered by");
				TextColumn<Order> adressColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return object.getOrderAdress();
					}
				};
				orderTable.addColumn(adressColumn, "Adress");
				TextColumn<Order> orderDateColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return object.getOrderDate();
					}
				};
				orderTable.addColumn(orderDateColumn, "Order date");
				TextColumn<Order> additionalNotesColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return object.getOrderDate();
					}
				};
				orderTable.addColumn(additionalNotesColumn, "Additional notes");
				TextColumn<Order> idStatusColumn = new TextColumn<Order>() {
					@Override
					public String getValue(Order object) {
						return String.valueOf(object.getStatusId());
					}
				};
				orderTable.addColumn(idStatusColumn, "Status");
				final SingleSelectionModel<Order> selectionModel = new SingleSelectionModel<Order>();
				orderTable.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Order selected = selectionModel.getSelectedObject();
						if (selected != null) {
							SERVICE.getOrderItemList(String.valueOf(selected.getOrderId()), new AsyncCallback<List<OrderItems>>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Method 'getOrderItemList' has failed.");
								}

								@Override
								public void onSuccess(List<OrderItems> result) {
									if (!firstTimeHistory) {
										rootPanel.remove(2);
									}
									firstTimeHistory = false;
									historyOrderItemList = result;
									fillOrderItemsTable(false, true);
								}
							});
						}
					}
				});
				orderTable.setPageSize(100);
				orderTable.setRowCount(orderList.size(), false);
				orderTable.setRowData(0, orderList);
				rootPanel.add(orderTable);
			}
		});
	}

	//================================================================================
	// Fill lists with data from database and create list boxes for them
	//================================================================================
	private void fillLists() {
		SERVICE.getLists(new AsyncCallback<ReturnLists>() {

			@Override
			public void onSuccess(ReturnLists result) {
				itemList = result.getItemList();
				statusList = result.getStatusList();
				packageList = result.getPackageList();
				statusListBox = new ListBox();
				for (Status s : statusList) {
					statusListBox.addItem(s.getStatusName());
					//statusListBox2.addItem(s.getStatusName());
				}
				itemListBox = new ListBox();
				for (Item i : itemList) {
					itemListBox.addItem(i.getItemName());
				}
				packageListBox = new ListBox();
				for (Package p : packageList) {
					packageListBox.addItem(p.getPackageName());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Method 'getLists' has failed.");
			}
		});
	}

	//================================================================================
	// Item table
	//================================================================================
	private void fillOrderItemsTable(boolean reload, boolean history) {

		if (reload) {
			rootPanel.remove(2);
		}

		orderItemsTable = new CellTable<OrderItems>();
		orderItemsTable.setWidth("100%");
		orderItemsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		TextColumn<OrderItems> idOrderItemsColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getId_item());
			}
		};
		orderItemsTable.addColumn(idOrderItemsColumn, "Item ID");

		TextColumn<OrderItems> deadlineColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return object.getDeadline();
			}
		};
		orderItemsTable.addColumn(deadlineColumn, "Deadline");

		TextColumn<OrderItems> weightColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getWeight());
			}
		};
		orderItemsTable.addColumn(weightColumn, "Weight");

		TextColumn<OrderItems> deliveryColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getDelivery());
			}
		};
		orderItemsTable.addColumn(deliveryColumn, "Delivery");

		TextColumn<OrderItems> coolColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getCool());
			}
		};
		orderItemsTable.addColumn(coolColumn, "Cool");

		TextColumn<OrderItems> cutColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getCut());
			}
		};
		orderItemsTable.addColumn(cutColumn, "Cut");

		TextColumn<OrderItems> packageColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getId_package());
			}
		};
		orderItemsTable.addColumn(packageColumn, "Package");

		TextColumn<OrderItems> additionalNotesColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return object.getAdditionalNotes();
			}
		};
		orderItemsTable.addColumn(additionalNotesColumn, "Additional notes");

		TextColumn<OrderItems> deliveryTimeColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return object.getDeliveryTime();
			}
		};
		orderItemsTable.addColumn(deliveryTimeColumn, "Delivery time");

		TextColumn<OrderItems> amountColumn = new TextColumn<OrderItems>() {
			@Override
			public String getValue(OrderItems object) {
				return String.valueOf(object.getAmount());
			}
		};
		orderItemsTable.addColumn(amountColumn, "Amount");

		final SingleSelectionModel<OrderItems> selectionModel = new SingleSelectionModel<OrderItems>();
		orderItemsTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				OrderItems selected = selectionModel.getSelectedObject();
				if (selected != null) {
					selectedItem = selected;
				}
			}
		});

		if (history) {
			orderItemsTable.setRowCount(historyOrderItemList.size(), true);
			orderItemsTable.setRowData(0, historyOrderItemList);
		} else {
			orderItemsTable.setRowCount(orderItemList.size(), true);
			orderItemsTable.setRowData(0, orderItemList);
		}
		rootPanel.add(orderItemsTable);
	}

	//================================================================================
	// Main menu
	//================================================================================
	private void fillTimeListBox() {
		String[] timeList = { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" };
		timeListBox = new ListBox();
		deadlineDateTimeListBox = new ListBox();
		deliveryTimeListBox = new ListBox();
		for (String s : timeList) {
			timeListBox.addItem(s);
			deadlineDateTimeListBox.addItem(s);
			deliveryTimeListBox.addItem(s);
		}
	}

	//================================================================================
	// Dialog box used for adding and editing items
	//================================================================================
	private DialogBox getItemDialog(OrderItems orderItem) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Item");
		dialogBox.setAnimationEnabled(true);
		FlexTable addItemtable = new FlexTable();
		FlexTable checkBoxtable = new FlexTable();
		FlexTable deadlineTable = new FlexTable();
		FlexTable deliveryTable = new FlexTable();
		FlexTable buttonTable = new FlexTable();
		final Button okButton = new Button("Ok");
		final Button closeButton = new Button("Cancel");
		Panel returnPanel = new VerticalPanel();
		dialogBox.setWidget(returnPanel);
		final Label lblItem = new Label("Item:");
		final Label lblDeadline = new Label("Deadline:");
		final Label lblWeight = new Label("Weight:");
		final Label lblAmount = new Label("Amount:");
		final CheckBox checkBoxDelivery = new CheckBox("Delivery");
		final CheckBox checkBoxCool = new CheckBox("Cool");
		final CheckBox checkBoxCut = new CheckBox("Cut");
		final Label lblPackage = new Label("Package:");
		final Label lblNotes = new Label("Notes:");
		final Label lblDeliveryTime = new Label("Delivery time:");
		final TextBox weight = new TextBox();
		final TextBox amount = new TextBox();
		final TextArea additionalNotesArea = new TextArea();
		final DatePicker deadlineDatePicker = new DatePicker();
		final DatePicker deliveryDatePicker = new DatePicker();
		itemListBox.setWidth("300px");
		weight.setWidth("300px");
		amount.setWidth("300px");
		additionalNotesArea.setWidth("300px");
		additionalNotesArea.setHeight("150px");
		deadlineDatePicker.setWidth("300px");
		deadlineDateTimeListBox.setWidth("300px");
		deliveryDatePicker.setWidth("300px");
		deliveryTimeListBox.setWidth("300px");
		addItemtable.setWidget(0, 0, lblItem);
		addItemtable.setWidget(0, 1, itemListBox);
		addItemtable.setWidget(1, 0, lblDeadline);
		deadlineTable.setWidget(0, 0, deadlineDatePicker);
		deadlineTable.setWidget(1, 0, deadlineDateTimeListBox);
		addItemtable.setWidget(1, 1, deadlineTable);
		checkBoxtable.setWidget(2, 0, checkBoxDelivery);
		checkBoxtable.setWidget(2, 1, checkBoxCool);
		checkBoxtable.setWidget(2, 2, checkBoxCut);
		addItemtable.setWidget(2, 1, checkBoxtable);
		addItemtable.setWidget(3, 0, lblWeight);
		addItemtable.setWidget(3, 1, weight);
		addItemtable.setWidget(4, 0, lblAmount);
		addItemtable.setWidget(4, 1, amount);
		addItemtable.setWidget(5, 0, lblPackage);
		addItemtable.setWidget(5, 1, packageListBox);
		addItemtable.setWidget(6, 0, lblNotes);
		addItemtable.setWidget(6, 1, additionalNotesArea);
		addItemtable.setWidget(7, 0, lblDeliveryTime);
		deliveryTable.setWidget(0, 0, deliveryDatePicker);
		deliveryTable.setWidget(1, 0, deliveryTimeListBox);
		addItemtable.setWidget(7, 1, deliveryTable);
		buttonTable.setWidget(0, 0, okButton);
		buttonTable.setWidget(0, 1, closeButton);
		addItemtable.setWidget(8, 1, buttonTable);
		returnPanel.add(addItemtable);

		if (orderItem != null) {
			itemListBox.setSelectedIndex(getSelectedItemIndex(orderItem.getId_item()));
			checkBoxDelivery.setValue((orderItem.getDelivery() == 1) ? true : false);
			checkBoxCool.setValue((orderItem.getCool() == 1) ? true : false);
			checkBoxCut.setValue((orderItem.getCut() == 1) ? true : false);
			weight.setText(String.valueOf(orderItem.getWeight()));
			amount.setText(String.valueOf(orderItem.getAmount()));
			packageListBox.setSelectedIndex(getSelectedPackageIndex(orderItem.getId_package()));
			additionalNotes.setText(orderItem.getAdditionalNotes());
		}

		okButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (selectedItem != null) {
					orderItemList.remove(selectedItem);
				}

				newOrderItem = new OrderItems();
				newOrderItem.setId_item(new Item().getItemId(itemList, itemListBox.getSelectedValue()));
				newOrderItem.setDeadline(getSelectedTime(deadlineDatePicker, deadlineDateTimeListBox));
				newOrderItem.setStartTime(getSelectedTime(deadlineDatePicker, deadlineDateTimeListBox));
				newOrderItem.setWeight(Double.parseDouble(weight.getText()));
				newOrderItem.setDelivery((checkBoxDelivery.getValue()) ? 1 : 0);
				newOrderItem.setCool((checkBoxCool.getValue()) ? 1 : 0);
				newOrderItem.setCut((checkBoxCut.getValue()) ? 1 : 0);
				newOrderItem.setId_package(new Package().getPackageId(packageList, packageListBox.getSelectedValue()));
				newOrderItem.setAdditionalNotes(additionalNotesArea.getText());
				newOrderItem.setAmount(Integer.parseInt(amount.getText()));
				newOrderItem.setDeliveryTime(getSelectedTime(deliveryDatePicker, deliveryTimeListBox));
				orderItemList.add(newOrderItem);
				fillOrderItemsTable(true, false);
				cleanUI();
				displayAddOrder();
				dialogBox.hide();
			}
		});
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		return dialogBox;
	}

	//================================================================================
	// Used for selecting the correct item in the list box for edit item
	//================================================================================
	private int getSelectedItemIndex(int idItem) {
		int counter = 0;
		for (Item i : itemList) {
			if (i.getItemId() == idItem) {
				return counter;
			}
			counter++;
		}
		return counter;
	}

	//================================================================================
	// Used for selecting the correct package in the list box for edit item
	//================================================================================
	private int getSelectedPackageIndex(int idPackage) {
		int counter = 0;
		for (Package p : packageList) {
			if (p.getPackageId() == idPackage) {
				return counter;
			}
			counter++;
		}
		return counter;
	}

	//================================================================================
	// Parse time selection into string for database
	//================================================================================
	private String getSelectedTime(DatePicker datePicker, ListBox lb) {
		Date date = datePicker.getHighlightedDate();
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
		String dateString = fmt.format(date);
		String time = lb.getSelectedValue();
		String returnSelectedTime = dateString + " " + time + ":00";
		return returnSelectedTime;
	}

	//================================================================================
	// User interface cleanup
	//================================================================================
	public void cleanUI() {
		rootPanel.clear();
		rootPanel.add(menu);
		bottomPanel.clear();
	}

	//================================================================================
	// Display form used for order adding
	//================================================================================
	public void displayAddOrder() {
		if (!firstTimeAddOrder) {
			cleanUI();
		}
		firstTimeAddOrder = false;
		final Label lblOrderName = new Label("Name:");
		lblOrderName.setWidth("100px");
		orderNameField = new TextBox();
		orderNameField.setWidth("800px");
		orderNameField.setFocus(true);
		final Label lblAdress = new Label("Adress:");
		lblAdress.setWidth("100px");
		orderAdressField = new TextBox();
		orderAdressField.setWidth("800px");
		final Label lblOrderDate = new Label("Order date:");
		lblOrderDate.setWidth("100px");
		orderDatePicker = new DatePicker();
		orderDatePicker.setWidth("400px");
		fillTimeListBox();
		timeListBox.setWidth("400px");
		final Label lblStatus = new Label("Status:");
		lblStatus.setWidth("100px");
		statusListBox.setWidth("800px");
		final Label lblAdditionalNotes = new Label("Notes:");
		lblAdditionalNotes.setWidth("100px");
		additionalNotes = new TextArea();
		additionalNotes.setWidth("800px");
		additionalNotes.setHeight("150px");
		addOrderTable = new FlexTable();
		addOrderTable.setWidget(0, 0, lblOrderName);
		addOrderTable.setWidget(0, 1, orderNameField);
		addOrderTable.setWidget(1, 0, lblAdress);
		addOrderTable.setWidget(1, 1, orderAdressField);
		addOrderTable.setWidget(2, 0, lblOrderDate);
		addOrderTable.setWidget(2, 1, orderDatePicker);
		addOrderTable.setWidget(3, 1, timeListBox);
		addOrderTable.setWidget(4, 0, lblStatus);
		addOrderTable.setWidget(4, 1, statusListBox);
		addOrderTable.setWidget(5, 0, lblAdditionalNotes);
		addOrderTable.setWidget(5, 1, additionalNotes);
		rootPanel.add(addOrderTable);
		fillOrderItemsTable(false, false);

		//================================================================================
		// Save order click handler
		//================================================================================
		class SaveOrderHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				order = new Order();
				order.setOrderOrdered(orderNameField.getText());
				order.setOrderAdress(orderAdressField.getText());
				order.setOrderDate(getSelectedTime(orderDatePicker, timeListBox));
				order.setStatusId(new Status().getStatusId(statusList, statusListBox.getSelectedValue()));
				order.setAdditionalNotes(additionalNotes.getText());
				order.setUser_id(userId);
				order.setOrderId(0);
				SERVICE.addOrder(order, new AsyncCallback<Integer>() {

					@Override
					public void onSuccess(Integer result) {
						SERVICE.addOrderItems(orderItemList, result, new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(String result) {
								if (result.contains("Successfully added")) {
									Window.alert("Order added successfully");
								}
								GWT.log(result);
							}
						});

					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		}

		//================================================================================
		// Add item click handler
		//================================================================================
		class AddItemHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				DialogBox panel = getItemDialog(null);
				panel.show();
			}
		}

		//================================================================================
		// Edit item click handler
		//================================================================================
		class EditItemHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				DialogBox panel = getItemDialog(selectedItem);
				panel.show();
			}
		}

		//================================================================================
		// Add item click handler
		//================================================================================
		class RemoveItemHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				orderItemList.remove(selectedItem);
				fillOrderItemsTable(true, false);
				cleanUI();
				displayAddOrder();
			}
		}

		btnSendOrder = new PushButton();
		btnSendOrder.setText("Save order");
		btnSendOrder.setWidth("910px");
		btnAddItem = new PushButton();
		btnAddItem.setText("Add");
		btnAddItem.setWidth("100px");
		btnEditItem = new PushButton();
		btnEditItem.setText("Edit");
		btnEditItem.setWidth("100px");
		btnRemoveItem = new PushButton();
		btnRemoveItem.setText("Remove");
		btnRemoveItem.setWidth("100px");
		SaveOrderHandler saveOrderHandler = new SaveOrderHandler();
		AddItemHandler addItemHandler = new AddItemHandler();
		EditItemHandler editItemHandler = new EditItemHandler();
		RemoveItemHandler removeItemHandler = new RemoveItemHandler();
		btnSendOrder.addClickHandler(saveOrderHandler);
		btnAddItem.addClickHandler(addItemHandler);
		btnEditItem.addClickHandler(editItemHandler);
		btnRemoveItem.addClickHandler(removeItemHandler);
		orderItemButtonTable = new FlexTable();
		orderItemButtonTable.setWidget(0, 0, btnAddItem);
		orderItemButtonTable.setWidget(0, 1, btnEditItem);
		orderItemButtonTable.setWidget(0, 2, btnRemoveItem);
		rootPanel.add(orderItemButtonTable);
		bottomPanel.add(btnSendOrder);
	}
}
