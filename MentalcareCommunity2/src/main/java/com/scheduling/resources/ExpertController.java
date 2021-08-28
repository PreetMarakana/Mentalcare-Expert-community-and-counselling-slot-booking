package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.ExpertBookedSlotDto;
import com.scheduling.dto.ExpertDto;
import com.scheduling.dto.ExpertResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.ExpertService;
import com.scheduling.services.SlotServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpertController {
    
    @Autowired
    private ExpertService expertService;
    
    @Autowired
    private SlotServices slotService;

    @PostMapping(value =  "/secure/expert/expert",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity expertadd(@RequestBody ExpertDto expertDto,BindingResult results) {
        ResponseEntity responseEntity = new ResponseEntity();
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "expertName", "Name can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "expertEmail", "email can not be empty.");

        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
       boolean result =  expertService.createExpert(expertDto);
        if (result) {
            responseEntity.setStatus("done");
            responseEntity.setData("expert Added");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setStatus("not done");
            responseEntity.setData("?????");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
    
    @GetMapping(value = "/secure/expert/expertmeets",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity expertBookedSlots(@RequestParam("expertId") long expertId) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        List<ExpertBookedSlotDto> slotDetailsDtos =  slotService.getDoctorNextSevenDayBookedSlot(expertId);
        if (!slotDetailsDtos.isEmpty()) {
            responseEntity.setStatus("done");
            responseEntity.setData(slotDetailsDtos);
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setStatus("done");
            responseEntity.setData(slotDetailsDtos);
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
    }
    
    @GetMapping(value = "/expert/expertlist",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity expertList() {
        ResponseEntity responseEntity = new ResponseEntity();
        List<ExpertResponseDto> expertDtos =  expertService.getExpertList();
        if (expertDtos!=null) {
            responseEntity.setStatus("done");
            responseEntity.setData(expertDtos);
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setStatus("done");
            responseEntity.setData("????");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
    
    @GetMapping(value =  "/expert/{expertId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getExpertDetails(@PathVariable("expertId") long expertId) {
        ResponseEntity responseEntity = new ResponseEntity();
        ExpertResponseDto expertDto =  expertService.getExpertByExpertIdDto(expertId);
       if (expertDto!=null) {
           responseEntity.setStatus("done");
           responseEntity.setData(expertDto);
           responseEntity.setHttpStatus(HttpStatus.OK);
           return responseEntity;
       }else {
           responseEntity.setStatus("not done");
           responseEntity.setData("expert Id Not Exists");
           responseEntity.setHttpStatus(HttpStatus.OK);
           return responseEntity;
       }
    }
    
    
    
    
    
    
    
    
    
}
