package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.AvailableSlotDto;
import com.scheduling.dto.BookingDto;
import com.scheduling.dto.BookingRequestDto;
import com.scheduling.dto.CheckAvailaleDetailsDto;
import com.scheduling.dto.OrderRequestDto;
import com.scheduling.dto.OrderResponse;
import com.scheduling.dto.SlotDetailsDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.SlotBookingService;
import com.scheduling.services.SlotServices;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SlotAvailableResoureces {
    
    @Autowired
    private SlotServices slotservice;

    @Autowired
    private SlotBookingService slotBookingService;
    
    @PostMapping(value = "/secure/slotavailable/slotdetails" , produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSlotDetails(@RequestBody SlotDetailsDto slotDetailsDto, BindingResult results) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "fromDate", " can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "toDate", " can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "startTime", " can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "endTime", " can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "expert", " can not be empty.");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if (slotservice.addslotdetails(slotDetailsDto)) {
            responseEntity.setData("All records added");
            responseEntity.setStatus("Done");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("not done");
            responseEntity.setStatus("???");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        
    }
    
    @PostMapping(value = "/slotavailable/slotavailability" , produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkslotavailability(@RequestBody CheckAvailaleDetailsDto availaleDetailsDto, BindingResult results) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "date", " can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "expertId", " can not be empty.");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        List<AvailableSlotDto> availableSlotDtos = slotservice.checkSlotDetails(availaleDetailsDto);
        if (availableSlotDtos.size()!=0) {
            responseEntity.setData(availableSlotDtos);
            responseEntity.setStatus("Done");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("not done");
            responseEntity.setStatus("???");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        
    }
    
    @PostMapping(value = "/secure/slotavailable/slotbooking" , produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity slotbooking(@RequestBody BookingRequestDto bookingRequestDto, BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (userDetails!=null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(results, "slotId", "can not be empty.");
           
            if (results.hasErrors() || !slotservice.checkSlot(bookingRequestDto.getSlotId()) ) {
                responseEntity.setData(results.getAllErrors());
                responseEntity.setStatus("invalid slotId");
                responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
                return responseEntity;
            }
            OrderResponse order = slotBookingService.addBooking(bookingRequestDto,userDetails.getUsername());
            if (order!=null) {
                responseEntity.setData(order);
                responseEntity.setStatus("slot assigned");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }else {
                responseEntity.setData("Not available");
                responseEntity.setStatus("Already Booked");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }
            
        }else {
            responseEntity.setData("login first");
            responseEntity.setStatus("login");
            responseEntity.setHttpStatus(HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }
        
    }
  
    @PostMapping(value = "/secure/slot/slotbooking")
    public ResponseEntity slotePayment(@RequestBody OrderRequestDto order,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (userDetails!=null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(results, "orderId", "can not be empty.");
            ValidationUtils.rejectIfEmptyOrWhitespace(results, "paymentId", "can not be empty.");
            
           
            if (results.hasErrors()) {
                responseEntity.setData(results.getAllErrors());
                responseEntity.setStatus("invalid slotId");
                responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
                return responseEntity;
            }
            BookingDto book = slotBookingService.updateBooking(order,userDetails.getUsername());
            if (book!=null) {
                responseEntity.setData(book);
                responseEntity.setStatus("paid");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }else {
                responseEntity.setData("Something went wrong. may be paymentId or orderId does not exist");
                responseEntity.setStatus("try again");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }
            
        }else {
            responseEntity.setData("login first");
            responseEntity.setStatus("login");
            responseEntity.setHttpStatus(HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }
    }
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
}
