/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;


public class Meeting {
    private String  meetingDate;
    private String meetingTime;
    private String meetingProject;
    private String meetingRegistrationDate;
    private String statusMeeting;
    private String meetingNote;
    private String neetingPending;
    private MeetingAgenda meetingAgenda;
    private List<Agreement> agreements;

    public Meeting(String meetingDate, String meetingTime, String meetingProject, 
    String meetingRegistrationDate, String statusMeeting, String meetingNote, 
    String neetingPending) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingProject = meetingProject;
        this.meetingRegistrationDate = meetingRegistrationDate;
        this.statusMeeting = statusMeeting;
        this.meetingNote = meetingNote;
        this.neetingPending = neetingPending;
        this.meetingAgenda = new MeetingAgenda();
        this.agreements = new ArrayList();
    }

    public Meeting() {
        this.meetingAgenda = new MeetingAgenda();
        this.agreements = new ArrayList();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public String getMeetingProject() {
        return meetingProject;
    }

    public String getMeetingRegistrationDate() {
        return meetingRegistrationDate;
    }

    public String getStatusMeeting() {
        return statusMeeting;
    }

    public String getMeetingNote() {
        return meetingNote;
    }

    public String getNeetingPending() {
        return neetingPending;
    }

    public MeetingAgenda getMeetingAgenda() {
        return meetingAgenda;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setMeetingProject(String meetingProject) {
        this.meetingProject = meetingProject;
    }

    public void setMeetingRegistrationDate(String meetingRegistrationDate) {
        this.meetingRegistrationDate = meetingRegistrationDate;
    }

    public void setStatusMeeting(String statusMeeting) {
        this.statusMeeting = statusMeeting;
    }

    public void setMeetingNote(String meetingNote) {
        this.meetingNote = meetingNote;
    }

    public void setNeetingPending(String neetingPending) {
        this.neetingPending = neetingPending;
    }

    public void setMeetingAgenda(MeetingAgenda meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }
    
    public void addAgreement(Agreement newAgreement){
        agreements.add(newAgreement);
    }
    
    public void removeAgreement(Agreement agreement){
        agreements.remove(agreement);
    }
}
