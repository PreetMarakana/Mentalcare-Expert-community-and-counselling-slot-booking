package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SlotAvailable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long slotId;
    
    private Time stars;
    
    private Time ends;
   
    @ManyToOne
    @JsonManagedReference
    private ExpertAvailableDays expertAvailableDays;
    
    @ManyToOne
    @JsonBackReference
    private Expert expert;
    
    private Date sloDate;
    
    private boolean available;

    public SlotAvailable() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SlotAvailable(@NotEmpty Time stars, @NotEmpty Time ends, Expert expert, Date sloDate, boolean available) {
        super();
        this.stars = stars;
        this.ends = ends;
        this.expert = expert;
        this.sloDate = sloDate;
        this.available = true;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public Time getStars() {
        return stars;
    }

    public void setStars(Time stars) {
        this.stars = stars;
    }

    public Time getEnds() {
        return ends;
    }

    public void setEnds(Time ends) {
        this.ends = ends;
    }

    public ExpertAvailableDays getExpertAvailableDays() {
        return expertAvailableDays;
    }

    public void setExpertAvailableDays(ExpertAvailableDays expertAvailableDays) {
        this.expertAvailableDays = expertAvailableDays;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Date getSloDate() {
        return sloDate;
    }

    public void setSloDate(Date sloDate) {
        this.sloDate = sloDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "SlotAvailable [slotId=" + slotId + ", stars=" + stars + ", ends=" + ends + ", expertAvailableDays="
                + expertAvailableDays + ", expert=" + expert + ", sloDate=" + sloDate + ", available=" + available
                + "]";
    }
    
}
