package com.scheduling.notification;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PushNotificationService {

    
//    @Value("#{${app.notifications.defaults}}")
//    private Map<String, String> defaults;

    @Autowired
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    

    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(getSamplePayloadData(), request);
        } catch (Exception e) {
        }
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (Exception e) {
        }
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
//            request.setToken("e9dXvAm-QMKsHWQrtHAQEk:APA91bEmOA26oKzsUDnEZEeNSigNew2B7mBt_wGRjYSVw3h6PtT3ODyx5-r9QMXaN9J14Jrp5RCrQ-N9vlBqmhhe2X_lrku1MS2QIeA_ZVqyoKGgVyAXT3gZGXA-tZZBjgtK8KeZICG_");
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId", "msgid");
        pushData.put("text", "txt");
        pushData.put("user", "pankaj singh");
        return pushData;
    }
    
    
}
