package com.thop.webclient.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import com.thop.webclient.client.clientObjects.ReturnLists;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	int login(String username, String password) throws IllegalArgumentException;

	List<Order> getOrderList(int userId);

	ReturnLists getLists();

	Integer addOrder(Order order);
	
	String addOrderItems(List<OrderItems> orderItemsList, int orderId);
	
}
