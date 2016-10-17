package com.joseoliveros.order.bo;

import com.joseoliveros.order.bo.exception.BOException;
import com.joseoliveros.order.dto.Order;

public interface OrderBO {

    boolean placeOrder(Order order) throws BOException;

    boolean cancelOrder(int id) throws BOException;

    boolean deleteOrder(int id) throws BOException;
}
