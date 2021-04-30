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
    private String responsibleAgreement;
    private String deliveryDate;

    public Agreement(int agreementNumber, String meetingDate, String meetingTime, 
    String descriptionAgreement, String responsibleAgreement, String deliveryDate) {
        this.agreementNumber = agreementNumber;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.descriptionAgreement = descriptionAgreement;
        this.responsibleAgreement = responsibleAgreement;
        this.deliveryDate = deliveryDate;
    }

    public int getAgreementNumber() {
        return agreementNumber;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public String getDescriptionAgreement() {
        return descriptionAgreement;
    }

    public String getResponsibleAgreement() {
        return responsibleAgreement;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setAgreementNumber(int agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setDescriptionAgreement(String descriptionAgreement) {
        this.descriptionAgreement = descriptionAgreement;
    }

    public void setResponsibleAgreement(String responsibleAgreement) {
        this.responsibleAgreement = responsibleAgreement;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
