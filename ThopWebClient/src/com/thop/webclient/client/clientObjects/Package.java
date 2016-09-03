package com.thop.webclient.client.clientObjects;

import java.io.Serializable;
import java.util.List;

public class Package implements Serializable {

	private static final long serialVersionUID = -8142460749162341265L;
	private int packageId;
	private String packageName;
	private String packageDescription;

	public Package() {
		super();
	}

	public Package(int packageId, String packageName, String packageDescription) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
	}

	public int getPackageId(List<Package> packageList, String packageName) {
		for (Package p : packageList) {
			if (p.getPackageName().equalsIgnoreCase(packageName)) {
				return p.getPackageId();
			}
		}
		return -1;
	}

	public String getPackageName(List<Package> packageList, int packageId) {
		for (Package p : packageList) {
			if (p.getPackageId() == packageId) {
				return p.getPackageName();
			}
		}
		return null;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

}
