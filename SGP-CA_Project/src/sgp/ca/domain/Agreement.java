/**
* @author Johann 
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

public class Agreement {
    private int agreementNumber;
    private String meetingDate;
    private String meetingTime;
    private String descriptionAgreement;
    private String deliveryDate;

    public Agreement(int agreementNumber, String meetingDate, String meetingTime, String descriptionAgreement, String deliveryDate) {
        this.agreementNumber = agreementNumber;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.descriptionAgreement = descriptionAgreement;
        this.deliveryDate = deliveryDate;
    }
   
    public int getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(int agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getDescriptionAgreement() {
        return descriptionAgreement;
    }

    public void setDescriptionAgreement(String descriptionAgreement) {
        this.descriptionAgreement = descriptionAgreement;
    }
    
    
}
