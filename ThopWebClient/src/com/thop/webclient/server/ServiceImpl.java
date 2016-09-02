package com.thop.webclient.server;

import com.thop.webclient.client.Service;

import implementations.LoginImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	public String login(String username, String password) throws IllegalArgumentException {
		
		LoginImpl li = new LoginImpl();
		int id = li.login(username, password);
		return "Hello, " + username + " your ID is: " + id;

	}
}
