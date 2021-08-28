package com.scheduling.serviceImplimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.ReportDto;
import com.scheduling.model.Report;
import com.scheduling.repository.ReportRepository;
import com.scheduling.services.ReportService;

@Service
public class ReportImplimentation implements ReportService {

    @Autowired
    private ReportRepository repository;
    
    @Override
    public void saveData(ReportDto reportDto, UserDetails userDetails) {
        try {
                Report report = new Report();
                report.setPostId(reportDto.getId());
                report.setType(reportDto.getType());
                report.setUser(reportDto.getUser());
                repository.save(report);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    
    
}
