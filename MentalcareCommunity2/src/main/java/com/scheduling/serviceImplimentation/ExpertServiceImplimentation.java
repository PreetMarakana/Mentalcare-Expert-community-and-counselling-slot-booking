package com.scheduling.serviceImplimentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.Exception.SqlQueryException;
import com.scheduling.dto.ExpertDto;
import com.scheduling.dto.ExpertResponseDto;
import com.scheduling.dto.SlotDetailsDto;
import com.scheduling.model.Expert;
import com.scheduling.repository.ExpertRepository;
import com.scheduling.services.ExpertService;

@Service
public class ExpertServiceImplimentation implements ExpertService {

    @Autowired
    private ExpertRepository expertRepository;
    
    
    
    @Override
    public Expert getExpertByExpertId(long expertId) {
        try {
            Optional<Expert> expert = expertRepository.findById(expertId);
            if (expert.isPresent()) {
                return expert.get();
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("Expert not find query exception");
        }
    }

    @Override
    public boolean createExpert(ExpertDto expertDto) {
        try {
            Expert expert = new Expert();
            expert.setConversationCount(11);
            expert.setExpertProfile("dsad.dsadsad");
//            AwsService.uploadimage(expert.getExpertEmail()+expertDto.getExpertProfile().getOriginalFilename(),expertDto.getExpertProfile().getBytes())
            expert.setExpertName(expertDto.getExpertName());
            expert.setExpertType("Relationship stress expert");
            expert.setExpertEmail(expertDto.getExpertEmail());
            expertRepository.save(expert);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SlotDetailsDto> getExpertBookedSlots() {
        
        
        return null;
    }

    @Override
    public List<ExpertResponseDto> getExpertList() {
        try {
            List<Expert> experts = expertRepository.findAll();
            List<ExpertResponseDto> expertDtos = new ArrayList<>();
            experts.stream().forEach(expert -> {
                expertDtos.add(new ExpertResponseDto(expert.getExpertId(),expert.getExpertName(), expert.getExpertEmail(),expert.getExpertType(),expert.getConversationCount(),expert.getRating(),expert.getExpertProfile()));
            });
            
            if (expertDtos!=null) {
                return expertDtos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }

    @Override
    public ExpertResponseDto getExpertByExpertIdDto(long expertId) {
        try {
            Optional<Expert> expert = expertRepository.findById(expertId);
            if (expert.isPresent()) {
                return new ExpertResponseDto(expertId,expert.get().getExpertName(), expert.get().getExpertEmail(), expert.get().getExpertType(), expert.get().getConversationCount(), expert.get().getRating(),expert.get().getExpertProfile());
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("Expert not find query exception");
        }
    }

    
}
