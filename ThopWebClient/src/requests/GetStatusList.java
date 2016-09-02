package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.thop.webclient.client.clientObjects.Status;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetStatusList {

	OkHttpClient client;

	public GetStatusList() {
		client = new OkHttpClient();
	}

	public List<Status> getStatusList() {
		Gson gson = new GsonBuilder().create();
		Request request = new Request.Builder().url(Urls.GET_ALL_STATUS).build();
		try (Response response = client.newCall(request).execute()) {
			String responseJson = response.body().string();
			//System.out.println(responseJson);
			Status[] oi = gson.fromJson(responseJson, Status[].class);
			List<Status> statusList = new ArrayList<Status>();
			for(Status s : oi){
				statusList.add(s);
			}
			return statusList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
