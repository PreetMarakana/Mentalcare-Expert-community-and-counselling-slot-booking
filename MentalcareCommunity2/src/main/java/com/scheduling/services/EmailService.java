package com.scheduling.services;

import org.springframework.stereotype.Service;

import com.scheduling.dto.BookingDto;

@Service
public interface EmailService  {

    void sendmail(String userEmail, String otpValue);

    void sendBookingMail(String userEmail, BookingDto converter);

}
