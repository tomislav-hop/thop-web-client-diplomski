package gsonObjects;

public class Item {

	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private Double itemTimePerKg;

	public Item(Integer itemId, String itemName, String itemDescription, Double itemTimePerKg) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemTimePerKg = itemTimePerKg;
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
