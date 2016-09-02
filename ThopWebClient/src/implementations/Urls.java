package implementations;

import okhttp3.MediaType;

public class Urls {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final String START_URL = "http://localhost:8080/ThopWebService/rest/";
	public static final String LOGIN_URL = START_URL + "user/login";
	public static final String GET_ALL_STATUS = START_URL + "status/getAll";
	public static final String GET_STATUS = START_URL + "status/get?statusId=";
	public static final String ADD_STATUS = START_URL + "status/add";
	public static final String ADD_ITEM = START_URL + "item/add";
	public static final String GET_ITEM = START_URL + "item/get?itemId=";
	public static final String GET_ALL_ITEMS = START_URL + "item/getAll";
	public static final String GET_PACKAGE = START_URL + "package/get?packageId=";
	public static final String GET_ALL_PACKAGES = START_URL + "package/getAll";
	public static final String ADD_PACKAGE = START_URL + "package/add";
	public static final String ADD_ORDER = START_URL + "order/add";
	public static final String GET_ALL_ORDERS = START_URL + "order/getAll?userId=";
	public static final String GET_ORDER = START_URL + "order/get?orderId=";
	public static final String ADD_FULL_ORDER = START_URL + "orderItems/add?orderId=";
	public static final String GET_FULL_ORDER = START_URL + "orderItems/getAll?orderId=";
	public static final String GET_ITEM_BAKE_TIME_P1 = START_URL + "item/bake?kg=";
	public static final String GET_ITEM_BAKE_TIME_P2 = "&itemId=";
}
