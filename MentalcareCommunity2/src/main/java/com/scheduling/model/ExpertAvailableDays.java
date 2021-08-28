package com.scheduling.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ExpertAvailableDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dayId;
    
    @NotEmpty
    private String dayName;
    
    @ManyToOne
    @JsonBackReference
    private Expert expert;
    
    @OneToMany(mappedBy = "expertAvailableDays",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<SlotAvailable> slotAvailables;

    public ExpertAvailableDays() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ExpertAvailableDays(long dayId, @NotEmpty String dayName, Expert expert,
            List<SlotAvailable> slotAvailables) {
        super();
        this.dayId = dayId;
        this.dayName = dayName;
        this.expert = expert;
        this.slotAvailables = slotAvailables;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public List<SlotAvailable> getSlotAvailables() {
        return slotAvailables;
    }

    public void setSlotAvailables(List<SlotAvailable> slotAvailables) {
        this.slotAvailables = slotAvailables;
    }

    @Override
    public String toString() {
        return "ExpertAvailableDays [dayId=" + dayId + ", dayName=" + dayName + ", expert=" + expert
                + ", slotAvailables=" + slotAvailables + "]";
    }
    
}
