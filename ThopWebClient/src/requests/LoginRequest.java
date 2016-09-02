package requests;

import java.io.IOException;

import com.google.gson.Gson;

import gsonObjects.User;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRequest {

	OkHttpClient client;

	public LoginRequest() {
		client = new OkHttpClient();
	}

	public int sendLoginRequest(String username, String password) {
		User user = new User(username, password);
		String requestJson = new Gson().toJson(user);
		RequestBody body = RequestBody.create(Urls.JSON, requestJson);
		Request request = new Request.Builder().url(Urls.LOGIN_URL).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String returnResponse = response.body().string();
			if (returnResponse.equalsIgnoreCase("Fail")) {
				return -1;
			} else {
				return Integer.parseInt(returnResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
