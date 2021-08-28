package com.scheduling.dto;

public class OtpDto {

    private String otpValue;

    public OtpDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OtpDto(String otpValue) {
        super();
        this.otpValue = otpValue;
    }

    public String getOtpValue() {
        return otpValue;
    }

    public void setOtpValue(String otpValue) {
        this.otpValue = otpValue;
    }

    @Override
    public String toString() {
        return "OtpDto [otpValue=" + otpValue + "]";
    }
    
}
