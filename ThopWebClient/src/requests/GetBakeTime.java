package requests;

import java.io.IOException;

import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetBakeTime {
	OkHttpClient client;

	public GetBakeTime() {
		client = new OkHttpClient();
	}

	public String getBakeTimeResponse(int itemId, String kg) {
		Request request = new Request.Builder().url(Urls.GET_ITEM_BAKE_TIME_P1 + kg + Urls.GET_ITEM_BAKE_TIME_P2 + itemId).build();
		try (Response response = client.newCall(request).execute()) {
			String responseJson = response.body().string();
			return responseJson;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
