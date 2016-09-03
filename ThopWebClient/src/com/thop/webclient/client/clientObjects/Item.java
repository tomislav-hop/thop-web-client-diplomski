package com.thop.webclient.client.clientObjects;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
	private static final long serialVersionUID = -3283209333708834470L;
	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private Double itemTimePerKg;

	public Item() {
		super();
	}

	public Item(Integer itemId, String itemName, String itemDescription, Double itemTimePerKg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemTimePerKg = itemTimePerKg;
	}
	
	public int getItemId(List<Item> itemList, String itemName) {

		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(itemName)) {
				return i.getItemId();
			}
		}
		return -1;
	}

	public String getItemName(List<Item> itemList, int itemId) {

		for (Item i : itemList) {
			if (i.getItemId() == itemId) {
				return i.getItemName();
			}
		}
		return null;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Double getItemTimePerKg() {
		return itemTimePerKg;
	}

	public void setItemTimePerKg(Double itemTimePerKg) {
		this.itemTimePerKg = itemTimePerKg;
	}

	@Override
	public String toString() {
		return "{ Naziv: " + itemName + "\tOpis: " + itemDescription + "\tVrijemePoKg: " + itemTimePerKg + " }";
	}
}
