package com.scheduling.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduling.dto.BookingDto;
import com.scheduling.dto.BookingRequestDto;
import com.scheduling.dto.OrderRequestDto;
import com.scheduling.dto.OrderResponse;

@Service
public interface SlotBookingService {

    OrderResponse addBooking(BookingRequestDto slotId, String username);

    List<BookingDto> getUserBookedSlotByEmail(String username);

    BookingDto updateBooking(OrderRequestDto order,String username);

}
