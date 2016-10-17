package com.joseoliveros.order.bo.exception;

import java.sql.SQLException;

public class BOException extends Exception {
    
    private static final long serialVersionUID = -126714924067074374L;

    public BOException(SQLException e) {
        super(e);
    }
}
