package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderTableDao;
import com.favery.model.orderTable;
import com.favery.util.MyConnection;

public class OrderTableDaoImpl implements OrderTableDao {
    
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<orderTable> orderList = new ArrayList<>();
    private orderTable order = null;

    private static final String INSERT_QUERY = "INSERT INTO `ordertable` (`restaurantId`, `userId`, `totalAmount`, `status`, `paymentMode`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `ordertable` WHERE `orderId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `ordertable`";

    int status = 0;

    public OrderTableDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrder(orderTable order) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setFloat(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public orderTable getOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderId);
            res = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(res);
            if (!orderList.isEmpty()) {
                order = orderList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<orderTable> getAllOrders() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private List<orderTable> extractOrderFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int orderId = res.getInt("orderId");
                int restaurantId = res.getInt("restaurantId");
                int userId = res.getInt("userId");
                float totalAmount = res.getFloat("totalAmount");
                String status = res.getString("status");
                String paymentMode = res.getString("paymentMode");

                order = new orderTable(orderId, restaurantId, userId, totalAmount, status, paymentMode);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
