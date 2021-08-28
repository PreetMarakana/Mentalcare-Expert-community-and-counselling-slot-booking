package com.scheduling.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Expert;
import com.scheduling.model.SlotAvailable;

@Repository
public interface SlotRepository extends JpaRepository<SlotAvailable, Long> {

    @Query(value = "select sa from SlotAvailable sa where sa.expert=?2 AND sa.sloDate=?1")
    List<SlotAvailable> getAvailableSlotDetails(Date slotDate, Expert expert);

    @Query(value = "select sa from SlotAvailable sa where sa.expert=:expert AND sa.available=:b AND sa.sloDate BETWEEN :currentDate AND :sevendayAfter")
    List<SlotAvailable> getDoctorNextSevenDayBookedSlot(@Param("expert") Expert expert,@Param("currentDate") Date currentDate,@Param("sevendayAfter") Date sevendayAfter,@Param("b") boolean b);

//    New com.scheduling.dto.AvailableSlotDto(sa.slotId,sa.stars,sa.ends,sa.sloDate)
}
