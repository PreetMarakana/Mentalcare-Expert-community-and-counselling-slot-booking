package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.Exception.SqlQueryException;
import com.scheduling.dto.AvailableSlotDto;
import com.scheduling.dto.CheckAvailaleDetailsDto;
import com.scheduling.dto.ExpertBookedSlotDto;
import com.scheduling.dto.SlotDetailsDto;
import com.scheduling.model.Expert;
import com.scheduling.model.SlotAvailable;
import com.scheduling.repository.SlotRepository;
import com.scheduling.services.ExpertService;
import com.scheduling.services.SlotServices;

@Service
public class SlotServiceImplimentation implements SlotServices {

    @Autowired
    private SlotRepository slotRepository;
    
    @Autowired
    private ExpertService expertService;
    
    @Override
    public boolean addslotdetails(SlotDetailsDto slotDetailsDto) {
        
        try {
            
            Expert expert = expertService.getExpertByExpertId(slotDetailsDto.getExpert());
            List<SlotAvailable> slotAvailables = new ArrayList<>();
            
            String startDate[] = slotDetailsDto.getFromDate().split("-");
            String endDate[] = slotDetailsDto.getToDate().split("-");
            String startTime[] = slotDetailsDto.getStartTime().split(":");
            String endTime[] = slotDetailsDto.getEndTime().split(":");
            
            if (Integer.valueOf(startDate[2])>2021 || Integer.valueOf(startDate[1])<1 || Integer.valueOf(startDate[1])>12 || Integer.valueOf(startDate[0])<1 || Integer.valueOf(startDate[1])>31) {
                return false;
            }
            if (Integer.valueOf(endDate[2])>2021 || Integer.valueOf(endDate[1])<1 || Integer.valueOf(endDate[1])>12 || Integer.valueOf(endDate[0])<1 || Integer.valueOf(endDate[1])>31) {
                return false;
            }
            
            
            List<LocalTime> slots = new ArrayList<>() ;
            
            LocalTime statsTime = LocalTime.of(Integer.valueOf(startTime[0]), Integer.valueOf(startTime[1]));
            LocalTime endsTime = LocalTime.of(Integer.valueOf(endTime[0]), Integer.valueOf(endTime[1]));
            while (statsTime.isBefore(endsTime)) {
                slots.add(statsTime);
                statsTime= statsTime.plusHours(1);
            }
            
            LocalDate localStart = LocalDate.of(Integer.valueOf(startDate[2]) ,Integer.valueOf(startDate[1]),Integer.valueOf(startDate[0]));
            LocalDate localEnd = LocalDate.of(Integer.valueOf(endDate[2]) ,Integer.valueOf(endDate[1]),Integer.valueOf(endDate[0]));
            
            LocalDate next = localStart.minusDays(1);
            while ((next = next.plusDays(1)).isBefore(localEnd.plusDays(1))) {
                for (LocalTime localTime : slots) {
                    slotAvailables.add(new SlotAvailable(Time.valueOf(localTime), Time.valueOf(localTime.plusHours(1)), expert, Date.valueOf(next), true));
                }
            }
            
            slotRepository.saveAll(slotAvailables);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new SqlQueryException("slot creation time");    
        }
    }

    @Override
    public List<AvailableSlotDto> checkSlotDetails(CheckAvailaleDetailsDto availaleDetailsDto) {
        try {
            Expert expert = expertService.getExpertByExpertId(availaleDetailsDto.getExpertId());
            String startDate[] = availaleDetailsDto.getDate().split("-");
            if (Integer.valueOf(startDate[2])>2021 || Integer.valueOf(startDate[1])<1 || Integer.valueOf(startDate[1])>12 || Integer.valueOf(startDate[0])<1 || Integer.valueOf(startDate[1])>31) {
                return null;
            }
            Date slotDate = Date.valueOf(LocalDate.of(Integer.valueOf(startDate[2]),Integer.valueOf(startDate[1]), Integer.valueOf(startDate[0])));
            
            
            List<SlotAvailable> availableSlot = slotRepository.getAvailableSlotDetails(slotDate,expert);
            List<AvailableSlotDto> availableSlotDtos = convertor(availableSlot);
            
            if (availableSlotDtos!=null) {
                return availableSlotDtos;
            }
            return new ArrayList<>();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new SqlQueryException("available slot query error");
        }
    }

    private List<AvailableSlotDto> convertor(List<SlotAvailable> availableSlot) {
        
        List<AvailableSlotDto> availableSlotDtos = new ArrayList<>();
        
        availableSlot.stream().forEach(sa -> {
            availableSlotDtos.add(new com.scheduling.dto.AvailableSlotDto(sa.getSlotId(),sa.getStars(),sa.getEnds(),sa.getSloDate(),sa.isAvailable()));
        });
        
        return availableSlotDtos;
    }

    @Override
    public boolean checkSlot(long slotId) {
        try {
            Optional<SlotAvailable> available = slotRepository.findById(slotId);
            if (available.isPresent() && available.get().isAvailable()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("slot check error");
        }
        return false;
    }

    @Override
    public SlotAvailable getslotByID(long slotId) {
        try {
            Optional<SlotAvailable> available = slotRepository.findById(slotId);
            if (available.isPresent() && available.get().isAvailable()) {
                return available.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("slot check error");
        }
        
        return null;
    }

    @Override
    public boolean bookslot(SlotAvailable slotAvailable) {
        
        try {
            if ( slotAvailable!=null && slotAvailable.isAvailable()) {
                slotAvailable.setAvailable(false);
                slotRepository.save(slotAvailable);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("book slot exception");
        }
        
    }

    @Override
    public List<ExpertBookedSlotDto> getDoctorNextSevenDayBookedSlot(long expertId) {
        try {
            List<ExpertBookedSlotDto> detailsDtos = new ArrayList<>();
            Expert expert = expertService.getExpertByExpertId(expertId);
            Date currentDate = Date.valueOf(LocalDate.now());
            Date sevendayAfter = Date.valueOf(LocalDate.now().plusDays(7));
            if (expert!=null) {
                List<SlotAvailable> slotAvailables = slotRepository.getDoctorNextSevenDayBookedSlot(expert,currentDate,sevendayAfter,false);
                slotAvailables.stream().forEach(slotAvailable -> {
                    detailsDtos.add(new ExpertBookedSlotDto(slotAvailable.getStars(),slotAvailable.getEnds(), slotAvailable.getSloDate()));
                });
            }
            return detailsDtos;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        
    }


}
