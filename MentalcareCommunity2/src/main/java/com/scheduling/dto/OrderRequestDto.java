package com.scheduling.dto;

public class OrderRequestDto {

    private String orderId;
    
    private String paymentId;

    public OrderRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OrderRequestDto(String orderId, String paymentId) {
        super();
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "OrderRequestDto [orderId=" + orderId + ", paymentId=" + paymentId + "]";
    }
    
}
