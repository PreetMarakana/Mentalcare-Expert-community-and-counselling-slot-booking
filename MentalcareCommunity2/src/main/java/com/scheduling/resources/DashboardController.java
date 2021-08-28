package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.BookingDto;
import com.scheduling.dto.UserDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.SlotBookingService;
import com.scheduling.services.UserServices;

@RestController
@RequestMapping("/secure/dashboard/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardController {

    @Autowired
    private UserServices userServices; 
    
    @Autowired
    private SlotBookingService slotBookingService;

    @GetMapping(value = "user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getuserprofile(@PathVariable("userId") long userId) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        UserDto userDto = userServices.getUserByUserIdDto(userId);
        
        if (userDto!=null) {
            responseEntity.setStatus("done");
            responseEntity.setData(userDto);
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setStatus("not done");
            responseEntity.setData("user Id not exists");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
    }
    
    @GetMapping(value = "user/booked/slot",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserBookedSlotList(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        List<BookingDto> slotDetailsDtos = slotBookingService.getUserBookedSlotByEmail(userDetails.getUsername());
        
        if (slotDetailsDtos!=null) {
            responseEntity.setStatus("done");
            responseEntity.setData(slotDetailsDtos);
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setStatus("not done");
            responseEntity.setData("user Id not exists");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
    }
    
    
    
    

}
