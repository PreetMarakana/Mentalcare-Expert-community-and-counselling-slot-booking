package com.scheduling.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expertId;
    
    @NotEmpty
    private String expertName;
    
    @NotEmpty
    private String expertEmail;
    
    private String expertType;
    
    private long conversationCount;
    
    private long rating;
    
    private String expertProfile;
    
    @OneToMany(mappedBy = "expert",cascade = CascadeType.ALL)
    private List<ExpertAvailableDays> expertAvailableDays;

    @OneToMany(mappedBy = "expert",cascade = CascadeType.ALL)
    private List<SlotBooking> slotBookings;
    
    public Expert() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Expert(long expertId, @NotEmpty String expertName, @NotEmpty String expertEmail) {
        super();
        this.expertId = expertId;
        this.expertName = expertName;
        this.expertEmail = expertEmail;
    }

    public Expert(long expertId, @NotEmpty String expertName, @NotEmpty String expertEmail, String expertType,
            long conversationCount, long rating, List<ExpertAvailableDays> expertAvailableDays,
            List<SlotBooking> slotBookings) {
        super();
        this.expertId = expertId;
        this.expertName = expertName;
        this.expertEmail = expertEmail;
        this.expertType = expertType;
        this.conversationCount = conversationCount;
        this.rating = rating;
        this.expertAvailableDays = expertAvailableDays;
        this.slotBookings = slotBookings;
    }

    public long getExpertId() {
        return expertId;
    }

    public void setExpertId(long expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertEmail() {
        return expertEmail;
    }

    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public long getConversationCount() {
        return conversationCount;
    }

    public void setConversationCount(long conversationCount) {
        this.conversationCount = conversationCount;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public List<ExpertAvailableDays> getExpertAvailableDays() {
        return expertAvailableDays;
    }

    public void setExpertAvailableDays(List<ExpertAvailableDays> expertAvailableDays) {
        this.expertAvailableDays = expertAvailableDays;
    }

    public List<SlotBooking> getSlotBookings() {
        return slotBookings;
    }

    public void setSlotBookings(List<SlotBooking> slotBookings) {
        this.slotBookings = slotBookings;
    }

    public String getExpertProfile() {
        return expertProfile;
    }

    public void setExpertProfile(String expertProfile) {
        this.expertProfile = expertProfile;
    }

    @Override
    public String toString() {
        return "Expert [expertId=" + expertId + ", expertName=" + expertName + ", expertEmail=" + expertEmail
                + ", expertType=" + expertType + ", conversationCount=" + conversationCount + ", rating=" + rating
                + ", expertAvailableDays=" + expertAvailableDays + ", slotBookings=" + slotBookings + "]";
    }

}
