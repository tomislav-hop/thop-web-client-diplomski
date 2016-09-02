package com.thop.webclient.server;

import com.thop.webclient.client.Service;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	public String greetServer(String input) throws IllegalArgumentException {
		return "Hello, " + input;

	}
}
