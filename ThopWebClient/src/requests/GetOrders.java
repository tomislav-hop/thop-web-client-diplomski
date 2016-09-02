package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.thop.webclient.client.clientObjects.Order;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetOrders {
	OkHttpClient client;

	public GetOrders() {
		client = new OkHttpClient();
	}

	public List<Order> getOrderList(int userId) {
		Gson gson = new GsonBuilder().create();
		Request request = new Request.Builder().url(Urls.GET_ALL_ORDERS + userId).build();
		try (Response response = client.newCall(request).execute()) {
			String responseJson = response.body().string();
			//System.out.println(responseJson);
			Order[] pArray = gson.fromJson(responseJson, Order[].class);
			List<Order> OrderList = new ArrayList<Order>();
			for(Order p : pArray){
				OrderList.add(p);
			}
			return OrderList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
