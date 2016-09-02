package com.thop.webclient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void login(String username, String password, AsyncCallback<String> callback) throws IllegalArgumentException;
}
