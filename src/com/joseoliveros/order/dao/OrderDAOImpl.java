package com.joseoliveros.order.dao;

import com.joseoliveros.order.dto.Order;

import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    
    @Override
    public int create(Order order) throws SQLException {
        return 0;
    }

    @Override
    public Order read(int id) throws SQLException {
        return null;
    }

    @Override
    public int update(Order order) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
