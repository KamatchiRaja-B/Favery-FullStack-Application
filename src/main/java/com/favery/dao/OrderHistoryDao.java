package com.favery.dao;

import java.util.List;
import com.favery.model.orderHistory;

public interface OrderHistoryDao {
    int addOrderHistory(orderHistory orderHistory);
    orderHistory getOrderHistory(int orderHistoryId);
    List<orderHistory> getAllOrderHistories();
}
