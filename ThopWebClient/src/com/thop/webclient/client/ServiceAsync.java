package com.thop.webclient.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.thop.webclient.client.clientObjects.Order;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void getOrderList(int userId, AsyncCallback<List<Order>> asyncCallback);

	void login(String username, String password, AsyncCallback<Integer> callback);
}
