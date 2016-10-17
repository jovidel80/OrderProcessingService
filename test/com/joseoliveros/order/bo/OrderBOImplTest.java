package com.joseoliveros.order.bo;

import com.joseoliveros.order.bo.exception.BOException;
import com.joseoliveros.order.dao.OrderDAO;
import com.joseoliveros.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderBOImplTest {

    public static final int ORDER_ID = 123;
    private OrderBOImpl bo;
    
    @Mock
    OrderDAO dao;

    @Before
    public void setup() {
        System.out.println("setup");
        MockitoAnnotations.initMocks(this);
        bo = new OrderBOImpl();
        bo.setDao(dao);
    }

    @Test
    public void placeOrder_Should_Create_And_Order() throws SQLException, BOException {
        System.out.println("placeOrder_Should_Create_And_Order");
        Order order = new Order();
        when(dao.create(order)).thenReturn(1);
        boolean result = bo.placeOrder(order);
        
        assertTrue(result);
        verify(dao).create(order);
    }

    @Test
    public void placeOrder_Should_Not_Create_And_Order() throws SQLException, BOException {
        System.out.println("placeOrder_Should_Not_Create_And_Order");
        Order order = new Order();
        when(dao.create(order)).thenReturn(0);
        boolean result = bo.placeOrder(order);

        assertFalse(result);
        verify(dao).create(order);
    }

    @Test(expected = BOException.class)
    public void placeOrder_Should_Throw_BOException() throws SQLException, BOException {
        System.out.println("placeOrder_Should_Not_Create_And_Order");
        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);
        boolean result = bo.placeOrder(order);
    }

    @Test
    public void candelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
        System.out.println("candelOrder_Should_Cancel_The_Order");
        Order order = new Order();
        when(dao.read(anyInt())).thenReturn(order);
        when(dao.update(order)).thenReturn(1);
        boolean result = bo.cancelOrder(ORDER_ID);

        assertTrue(result);

        verify(dao).read(ORDER_ID);
        verify(dao).update(order);
    }

    @Test
    public void candelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
        System.out.println("candelOrder_Should_Not_Cancel_The_Order");
        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean result = bo.cancelOrder(ORDER_ID);

        assertFalse(result);

        verify(dao).read(ORDER_ID);
        verify(dao).update(order);
    }

    @Test(expected = BOException.class)
    public void candelOrder_Should_Throw_BOException_OnRead() throws SQLException, BOException {
        System.out.println("candelOrder_Should_Throw_BOException_OnRead");
        when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
        bo.cancelOrder(ORDER_ID);
    }

    @Test(expected = BOException.class)
    public void candelOrder_Should_Throw_BOException_OnUpdate() throws SQLException, BOException {
        System.out.println("candelOrder_Should_Throw_BOException_OnUpdate");
        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        bo.cancelOrder(ORDER_ID);
    }

    @Test
    public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
        System.out.println("deleteOrder_Deletes_The_Order");
        when(dao.delete(ORDER_ID)).thenReturn(1);
        boolean result = bo.deleteOrder(ORDER_ID);

        assertTrue(result);
        verify(dao).delete(ORDER_ID);
    }

    @Test
    public void deleteOrder_Not_Deletes_The_Order() throws SQLException, BOException {
        System.out.println("deleteOrder_Not_Deletes_The_Order");
        when(dao.delete(ORDER_ID)).thenReturn(0);
        boolean result = bo.deleteOrder(ORDER_ID);

        assertFalse(result);
        verify(dao).delete(ORDER_ID);
    }
}