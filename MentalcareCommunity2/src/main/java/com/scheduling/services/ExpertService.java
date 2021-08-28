package com.scheduling.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduling.dto.ExpertDto;
import com.scheduling.dto.ExpertResponseDto;
import com.scheduling.dto.SlotDetailsDto;
import com.scheduling.model.Expert;

@Service
public interface ExpertService {

    Expert getExpertByExpertId(long expert);

    boolean createExpert(ExpertDto expertDto);

    List<SlotDetailsDto> getExpertBookedSlots();

    List<ExpertResponseDto> getExpertList();

    ExpertResponseDto getExpertByExpertIdDto(long expertId);

    
}
