package com.scheduling.Exception;

public class UserNotExists  extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public UserNotExists(String message) {
        super(message);
    }
}
