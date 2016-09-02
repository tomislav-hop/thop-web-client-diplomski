package com.thop.webclient.server;

import com.thop.webclient.client.Service;
import com.thop.webclient.client.clientObjects.Order;

import implementations.LoginImpl;
import implementations.OrderImpl;

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
}
