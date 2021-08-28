package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.SlotBooking;
import com.scheduling.model.UserEntity;

@Repository
public interface SlotBookingRepository extends JpaRepository<SlotBooking,Long> {


    @Query("select sb from SlotBooking sb where userEntity=?1")
    List<SlotBooking> findByUserEntitys(UserEntity userEntity);

    @Query("select o from SlotBooking o where orederId=?1")
    SlotBooking getByOrderId(String orderId);

}
