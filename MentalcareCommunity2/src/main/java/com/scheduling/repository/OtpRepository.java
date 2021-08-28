package com.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scheduling.model.OneTimePassword;
import com.scheduling.model.UserEntity;

@Repository
public interface OtpRepository extends JpaRepository<OneTimePassword, Long> {

    OneTimePassword findByotpValue(String otpValue);

    OneTimePassword findByuserEntity(UserEntity userByUserId);
    
}
