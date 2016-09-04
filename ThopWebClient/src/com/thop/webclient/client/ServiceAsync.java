package com.thop.webclient.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import com.thop.webclient.client.clientObjects.ReturnLists;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void getOrderList(int userId, AsyncCallback<List<Order>> asyncCallback);

	void login(String username, String password, AsyncCallback<Integer> callback);

	void getLists(AsyncCallback<ReturnLists> callback);

	void addOrder(Order order, AsyncCallback<Integer> callback);

	void addOrderItems(List<OrderItems> orderItemsList, int orderId, AsyncCallback<Boolean> callback);

	void getOrderItemList(String orderId, AsyncCallback<List<OrderItems>> callback);

	void getBakeTime(int itemId, String weight, AsyncCallback<String> callback);
}
