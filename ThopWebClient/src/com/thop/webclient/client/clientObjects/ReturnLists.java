package com.thop.webclient.client.clientObjects;

import java.io.Serializable;
import java.util.List;

public class ReturnLists implements Serializable {

	private static final long serialVersionUID = -2322562016532082046L;
	List<Status> statusList;
	List<Item> itemList;
	List<Package> packageList;

	public ReturnLists(List<Status> statusList, List<Item> itemList, List<Package> packageList) {
		super();
		this.statusList = statusList;
		this.itemList = itemList;
		this.packageList = packageList;
	}

	public ReturnLists() {
		super();
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

}
