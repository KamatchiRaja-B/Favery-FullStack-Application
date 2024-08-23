package com.favery.dao;

import java.util.List;
import com.favery.model.orderItem;

public interface OrderItemDao {
    int addOrderItem(orderItem item);
    orderItem getOrderItem(int orderItemId);
    List<orderItem> getAllOrderItems();
}
