package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderItemDao;
import com.favery.model.orderItem;
import com.favery.util.MyConnection;

public class OrderItemDaoImpl implements OrderItemDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<orderItem> orderItemList = new ArrayList<>();
    private orderItem orderItem = null;

    private static final String INSERT_QUERY = "INSERT INTO `orderitem` (`orderId`, `menuId`, `quantity`, `subTotal`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderitem`";

    int status = 0;

    public OrderItemDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrderItem(orderItem item) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setFloat(4, item.getSubTotal());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public orderItem getOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderItemId);
            res = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(res);
            if (!orderItemList.isEmpty()) {
                orderItem = orderItemList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<orderItem> getAllOrderItems() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    private List<orderItem> extractOrderItemFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int orderItemId = res.getInt("orderItemId");
                int orderId = res.getInt("orderId");
                int menuId = res.getInt("menuId");
                int quantity = res.getInt("quantity");
                float subTotal = res.getFloat("subTotal");

                orderItem = new orderItem(orderItemId, orderId, menuId, quantity, subTotal);
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
