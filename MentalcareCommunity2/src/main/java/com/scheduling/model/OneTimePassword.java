package com.scheduling.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long otpId;
    
    @NotEmpty
    private String otpValue;
    
    @JsonIgnore
    @OneToOne
    private UserEntity userEntity;
    
//    @NotEmpty
    private Date otpDate;
    
//    @NotEmpty
    private Time otpTime;

    
    
    public OneTimePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OneTimePassword(UserEntity userEntity) {
        super();
        this.otpValue = generateOtp();
        System.out.println(this.otpValue);
        this.userEntity = userEntity;
        this.otpDate = Date.valueOf(LocalDate.now());
        this.otpTime = Time.valueOf(LocalTime.now());
    }

    public long getOtpId() {
        return otpId;
    }

    public void setOtpId(long otpId) {
        this.otpId = otpId;
    }

    public String getOtpValue() {
        return otpValue;
    }

    public void setOtpValue(String otpValue) {
        this.otpValue = otpValue;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Date getOtpDate() {
        return otpDate;
    }

    public void setOtpDate(Date otpDate) {
        this.otpDate = otpDate;
    }

    public Time getOtpTime() {
        return otpTime;
    }

    public void setOtpTime(Time otpTime) {
        this.otpTime = otpTime;
    }

    @Override
    public String toString() {
        return "OneTimePassword [otpId=" + otpId + ", otpValue=" + otpValue + ", userEntity=" + userEntity
                + ", otpDate=" + otpDate + ", otpTime=" + otpTime + "]";
    }
    
    private String  generateOtp() {
        StringBuilder generatedOTP = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        try {

            secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

            for (int i = 0; i < 6; i++) {
                generatedOTP.append(secureRandom.nextInt(9));
            }
            if(generatedOTP.charAt(0)=='0') {
                generatedOTP.setCharAt(0, '7');
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedOTP.toString();
    }
    
}
