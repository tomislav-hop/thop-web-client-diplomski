package implementations;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.thop.webclient.client.clientObjects.Item;
import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import com.thop.webclient.client.clientObjects.Package;
import requests.AddOrder;
import requests.GetOrderItemsList;
import requests.GetOrders;

public class OrderImpl {

	public int sendAddOrderRequest(String orderedBy, String adress, String orderDate, String additionalNotes, int status_id, int user_id) {
		return new AddOrder().sendAddOrderRequest(orderedBy, adress, orderDate, additionalNotes, status_id, user_id);
	}

	public boolean addItemsToOrder(int idOrder, DefaultTableModel model, List<Item> itemList, List<Package> packageList) {
		return new AddOrder().addItemsToOrder(idOrder, model, itemList, packageList);
	}

	public List<Order> getOrderList(int userId) {
		return new GetOrders().getOrderList(userId);
	}

	public List<OrderItems> getOrderItemsList(String orderId) {
		return new GetOrderItemsList().getOrderItemsList(orderId);
	}

	public int parseCheckboxValue(String value) {
		if (value.equalsIgnoreCase("Yes")) {
			return 1;
		} else {
			return 0;
		}
	}

	public String[][] getTableData(DefaultTableModel dtm) {
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		String[][] tableData = new String[nRow][nCol];
		for (int i = 0; i < nRow; i++)
			for (int j = 0; j < nCol; j++)
				tableData[i][j] = dtm.getValueAt(i, j).toString();
		return tableData;
	}
}
