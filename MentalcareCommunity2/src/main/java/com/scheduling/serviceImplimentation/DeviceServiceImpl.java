package com.scheduling.serviceImplimentation;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.scheduling.model.DeviceData;
import com.scheduling.model.UserEntity;
import com.scheduling.repository.DeviceRepo;
import com.scheduling.services.DeviceService;

import ua_parser.Client;
import ua_parser.Parser;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String UNKNOWN = "UNKNOWN";

    @Autowired
    DatabaseReader databaseReader;

    @Autowired
    private Parser parser;

    @Autowired
    private DeviceRepo deviceRepo;

    @Override
    public void verifyDevice(UserEntity userEntity, HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ip = extractIp(request);
        String location = getIpLocation(ip);

        String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));

        DeviceData deviceData = findExistingDevice(userEntity.getUserId(), deviceDetails, location);

        if (deviceData == null) {
//            unknownDeviceNotification(deviceDetails, location, ip, user.getEmail(), request.getLocale());
            DeviceData data = new DeviceData();
            data.setDeviceDetails(deviceDetails);
            data.setLastLoggedIn(new Date(new java.util.Date().getTime()));
            data.setLocation(location);
            data.setUserId(userEntity.getUserId());
            deviceRepo.save(data);
        } else {
            deviceData.setLastLoggedIn(Date.valueOf(LocalDate.now()));
            deviceRepo.save(deviceData);
        }

    }

    private DeviceData findExistingDevice(Long userId, String deviceDetails, String location) {
        List<DeviceData> deviceDatas = deviceRepo.getDeviceDetailsByUserId(userId);

        for (DeviceData device : deviceDatas) {
            if (device.getDeviceDetails().equals(deviceDetails) && device.getLocation().equals(location)) {
                return device;
            }
        }

        return null;
    }

    private String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request.getHeader("x-forwarded-for");
        if (clientXForwardedForIp != null) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    private String getDeviceDetails(String userAgent) {
        String deviceDetails = UNKNOWN;

        Client client = parser.parse(userAgent);
        if (Objects.nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor
                    + " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
        }

        return deviceDetails;
    }

    public String getIpLocation(String ip) throws IOException, GeoIp2Exception {

        String location = UNKNOWN + ip;

        InetAddress ipAddress = InetAddress.getByName("150.129.164.219");
        if (databaseReader != null) {
            CityResponse cityResponse = databaseReader.city(ipAddress);
            if (Objects.nonNull(cityResponse) && Objects.nonNull(cityResponse.getCity())
                    && !Strings.isNullOrEmpty(cityResponse.getCity().getName())) {

                location = cityResponse.getCity().getName();
            }
        } else {
            System.out.println("not done");
        }

        return location;
    }

}
