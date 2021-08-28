package com.scheduling.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.ReportDto;

@Service
public interface ReportService {

    void saveData(ReportDto reportDto, UserDetails userDetails);

}
