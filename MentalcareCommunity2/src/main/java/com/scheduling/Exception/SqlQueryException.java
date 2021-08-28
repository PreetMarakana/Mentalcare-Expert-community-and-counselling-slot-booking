package com.scheduling.Exception;

public class SqlQueryException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public SqlQueryException(String message) {
        super(message);
    }
}
