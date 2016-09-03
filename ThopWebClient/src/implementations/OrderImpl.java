package implementations;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.thop.webclient.client.clientObjects.Order;
import com.thop.webclient.client.clientObjects.OrderItems;
import requests.AddOrder;
import requests.GetOrderItemsList;
import requests.GetOrders;

public class OrderImpl {

	public int sendAddOrderRequest(Order order) {
		return new AddOrder().sendAddOrderRequest(order);
	}

	public boolean addItemsToOrder(int orderId, List<OrderItems> orderItemsList) {
		return new AddOrder().addItemsToOrder(orderId, orderItemsList);
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
