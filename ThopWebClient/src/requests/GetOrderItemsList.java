package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.thop.webclient.client.clientObjects.OrderItems;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetOrderItemsList {
	OkHttpClient client;

	public GetOrderItemsList() {
		client = new OkHttpClient();
	}

	public List<OrderItems> getOrderItemsList(String orderId) {
		Gson gson = new GsonBuilder().create();
		Request request = new Request.Builder().url(Urls.GET_FULL_ORDER + orderId).build();
		try (Response response = client.newCall(request).execute()) {
			String responseJson = response.body().string();
			//System.out.println(responseJson);
			OrderItems[] oi = gson.fromJson(responseJson, OrderItems[].class);
			List<OrderItems> OrderItemsList = new ArrayList<OrderItems>();
			if (oi.length > 0) {
				for (OrderItems s : oi) {
					OrderItemsList.add(s);
				}
				return OrderItemsList;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
