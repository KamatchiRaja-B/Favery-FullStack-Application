package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.OrderHistoryDao;
import com.favery.model.orderHistory;
import com.favery.util.MyConnection;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet res = null;
    private List<orderHistory> orderHistoryList = new ArrayList<>();
    private orderHistory orderHistory = null;

    private static final String INSERT_QUERY = "INSERT INTO `orderhistory` (`orderId`, `userId`, `totalAmount`, `status`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderhistory` WHERE `orderHistoryId` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderhistory`";

    int status = 0;

    public OrderHistoryDaoImpl() {
        con = MyConnection.getInstance().getConnection();
    }

    @Override
    public int addOrderHistory(orderHistory orderHistory) {
        try {
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setFloat(3, orderHistory.getTotalAmount());
            pstmt.setString(4, orderHistory.getStatus());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public orderHistory getOrderHistory(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, orderHistoryId);
            res = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(res);
            if (!orderHistoryList.isEmpty()) {
                orderHistory = orderHistoryList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public List<orderHistory> getAllOrderHistories() {
        try {
            pstmt = con.prepareStatement(SELECT_ALL_QUERY);
            res = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    private List<orderHistory> extractOrderHistoryFromResultSet(ResultSet res) {
        try {
            while (res.next()) {
                int orderHistoryId = res.getInt("orderHistoryId");
                int orderId = res.getInt("orderId");
                int userId = res.getInt("userId");
                float totalAmount = res.getFloat("totalAmount");
                String status = res.getString("status");

                orderHistory = new orderHistory(orderHistoryId, orderId, userId, totalAmount, status);
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
