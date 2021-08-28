package com.scheduling.serviceImplimentation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpAttemptService {

    private static final int MAX_ATTEMPT = 5;
    private LoadingCache<String, Integer> otpAttemptsCache;
 
    public OtpAttemptService() {
        super();
        otpAttemptsCache = CacheBuilder.newBuilder().
          expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }
 
    public void otpSucceeded(String key) {
        otpAttemptsCache.invalidate(key);
    }
 
    public void otpFailed(String key) {
        int attempts = 0;
        try {
            attempts = otpAttemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        otpAttemptsCache.put(key, attempts);
    }
    
    public boolean isOtpAttemptExceed(String key) {
        try {
            return otpAttemptsCache.get(key) >= MAX_ATTEMPT;
         } catch (ExecutionException e) {
            return false;
        }
    }
}
