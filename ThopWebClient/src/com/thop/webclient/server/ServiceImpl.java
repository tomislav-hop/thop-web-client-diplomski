package com.thop.webclient.server;

import com.thop.webclient.client.Service;
import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import com.thop.webclient.client.clientObjects.ReturnLists;

import implementations.ItemImpl;
import implementations.LoginImpl;
import implementations.OrderImpl;
import implementations.PackageImpl;
import implementations.StatusImpl;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	public int login(String username, String password) throws IllegalArgumentException {
		LoginImpl li = new LoginImpl();
		int id = li.login(username, password);
		return id;

	}

	public List<Order> getOrderList(int userId) {
		OrderImpl oi = new OrderImpl();
		return oi.getOrderList(userId);
	}

	@Override
	public ReturnLists getLists() {
		ReturnLists returnLists = new ReturnLists();
		ItemImpl ii = new ItemImpl();
		returnLists.setItemList(ii.getItemList());
		PackageImpl pi = new PackageImpl();
		returnLists.setPackageList(pi.getPackageList());
		StatusImpl si = new StatusImpl();
		returnLists.setStatusList(si.getAllStatuses());
		return returnLists;
	}

	@Override
	public Integer addOrder(Order order) {
		OrderImpl oi = new OrderImpl();
		return oi.sendAddOrderRequest(order);
	}

	@Override
	public String addOrderItems(List<OrderItems> orderItemsList, int orderId) {
		OrderImpl oi = new OrderImpl();
		oi.addItemsToOrder(orderId, orderItemsList);
		return null;
	}

	@Override
	public List<OrderItems> getOrderItemList(String orderId) {
		OrderImpl oi = new OrderImpl();
		return oi.getOrderItemsList(orderId);
	}
}
