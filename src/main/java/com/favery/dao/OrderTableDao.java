package com.favery.dao;

import java.util.List;
import com.favery.model.orderTable;

public interface OrderTableDao {
    int addOrder(orderTable order);
    orderTable getOrder(int orderId);
    List<orderTable> getAllOrders();
}
