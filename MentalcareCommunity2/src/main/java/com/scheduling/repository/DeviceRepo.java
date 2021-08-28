package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.scheduling.model.DeviceData;

@Service
public interface DeviceRepo extends JpaRepository<DeviceData, Long> {

    @Query("from DeviceData where deviceId=?1")
    List<DeviceData> getDeviceDetailsByUserId(Long userId);

}
