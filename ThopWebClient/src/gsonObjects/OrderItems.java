package gsonObjects;

public class OrderItems {

    private int id_orderItems;
    private int id_item;
    private String deadline;
    private double weight;
    private String startTime;
    private int delivery;
    private int cool;
    private int cut;
    private int id_package;
    private String additionalNotes;
    private String deliveryTime;
    private int id_order;
    private int amount;

    public OrderItems(int id_orderItems, int id_item, String deadline, double weight, String startTime, int delivery,
	    int cool, int cut, int id_package, String additionalNotes, String deliveryTime, int id_order, int amount) {
	super();
	this.id_orderItems = id_orderItems;
	this.id_item = id_item;
	this.deadline = deadline;
	this.weight = weight;
	this.startTime = startTime;
	this.delivery = delivery;
	this.cool = cool;
	this.cut = cut;
	this.id_package = id_package;
	this.additionalNotes = additionalNotes;
	this.deliveryTime = deliveryTime;
	this.id_order = id_order;
	this.amount = amount;
    }

    public int getId_orderItems() {
	return id_orderItems;
    }

    public void setId_orderItems(int id_orderItems) {
	this.id_orderItems = id_orderItems;
    }

    public int getId_item() {
	return id_item;
    }

    public void setId_item(int id_item) {
	this.id_item = id_item;
    }

    public String getDeadline() {
	return deadline;
    }

    public void setDeadline(String deadline) {
	this.deadline = deadline;
    }

    public double getWeight() {
	return weight;
    }

    public void setWeight(double weight) {
	this.weight = weight;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public int getDelivery() {
	return delivery;
    }

    public void setDelivery(int delivery) {
	this.delivery = delivery;
    }

    public int getCool() {
	return cool;
    }

    public void setCool(int cool) {
	this.cool = cool;
    }

    public int getCut() {
	return cut;
    }

    public void setCut(int cut) {
	this.cut = cut;
    }

    public int getId_package() {
	return id_package;
    }

    public void setId_package(int id_package) {
	this.id_package = id_package;
    }

    public String getAdditionalNotes() {
	return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
	this.additionalNotes = additionalNotes;
    }

    public String getDeliveryTime() {
	return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
	this.deliveryTime = deliveryTime;
    }

    public int getId_order() {
	return id_order;
    }

    public void setId_order(int id_order) {
	this.id_order = id_order;
    }

    public int getAmount() {
	return amount;
    }

    public void setAmount(int amount) {
	this.amount = amount;
    }

}
