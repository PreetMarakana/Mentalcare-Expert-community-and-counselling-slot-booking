package com.scheduling.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.scheduling.dto.AvailableSlotDto;
import com.scheduling.dto.CheckAvailaleDetailsDto;
import com.scheduling.dto.ExpertBookedSlotDto;
import com.scheduling.dto.SlotDetailsDto;
import com.scheduling.model.SlotAvailable;

@Component
public interface SlotServices {

    boolean addslotdetails(SlotDetailsDto slotDetailsDto);

    List<AvailableSlotDto> checkSlotDetails(CheckAvailaleDetailsDto availaleDetailsDto);

    boolean checkSlot(long slotId);

    SlotAvailable getslotByID(long slotId);

    boolean bookslot(SlotAvailable slotAvailable);

    List<ExpertBookedSlotDto> getDoctorNextSevenDayBookedSlot(long expertId);


}
