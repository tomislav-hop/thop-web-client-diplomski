package com.thop.webclient.client.tableFunctions;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.thop.webclient.client.ThopWebClient;
import com.thop.webclient.client.clientObjects.Order;

public class GetOrdersTable {

	private CellTable<Order> orderTable;
	private int userId;
	private List<Order> orderList;

	private void getOrderHistoryForUser() {

		ThopWebClient.SERVICE.getOrderList(userId, new AsyncCallback<List<Order>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

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
				orderTable.addColumn(idStatusColumn, "Order ID");

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
			}
		});
	}
}
