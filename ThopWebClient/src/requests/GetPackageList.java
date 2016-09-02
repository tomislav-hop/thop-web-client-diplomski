package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.thop.webclient.client.clientObjects.Package;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPackageList {
	OkHttpClient client;

	public GetPackageList() {
		client = new OkHttpClient();
	}

	public List<Package> getPackageList() {
		Gson gson = new GsonBuilder().create();
		Request request = new Request.Builder().url(Urls.GET_ALL_PACKAGES).build();
		try (Response response = client.newCall(request).execute()) {
			String responseJson = response.body().string();
			//System.out.println(responseJson);
			Package[] pArray = gson.fromJson(responseJson, Package[].class);
			List<Package> packageList = new ArrayList<Package>();
			for(Package p : pArray){
				packageList.add(p);
			}
			return packageList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
