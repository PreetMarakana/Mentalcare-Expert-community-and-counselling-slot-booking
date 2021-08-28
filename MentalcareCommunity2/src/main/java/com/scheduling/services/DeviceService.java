package com.scheduling.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.scheduling.model.UserEntity;

@Service
public interface DeviceService {

    void verifyDevice(UserEntity principal, HttpServletRequest request) throws IOException, GeoIp2Exception;

}
