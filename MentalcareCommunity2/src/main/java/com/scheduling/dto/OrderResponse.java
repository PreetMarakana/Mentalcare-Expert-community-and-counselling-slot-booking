package com.scheduling.dto;

public class OrderResponse {

    private String orderId;

    private String status;

    public OrderResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OrderResponse(String orderId, String status) {
        super();
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderResponse [orderId=" + orderId + ", status=" + status + "]";
    }
    
    
    
}
