/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package domain;

import java.util.Date;

public class Meeting {
    private String  meetingDate;
    private String meetingRegistrationDate;
    private String meetingProject;
    private String meetingTime;
    private int meetingAgendaKey;

    public Meeting(String meetingDate, String meetingRegistrationDate, String meetingProject, String meetingTime, int meetingAgendaKey) {
        this.meetingDate = meetingDate;
        this.meetingRegistrationDate = meetingRegistrationDate;
        this.meetingProject = meetingProject;
        this.meetingTime = meetingTime;
        this.meetingAgendaKey = meetingAgendaKey;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingRegistrationDate() {
        return meetingRegistrationDate;
    }

    public void setMeetingRegistrationDate(String meetingRegistrationDate) {
        this.meetingRegistrationDate = meetingRegistrationDate;
    }

    public String getMeetingProject() {
        return meetingProject;
    }

    public void setMeetingProject(String meetingProject) {
        this.meetingProject = meetingProject;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public int getMeetingAgendaKey() {
        return meetingAgendaKey;
    }

    public void setMeetingAgendaKey(int meetingAgendaKey) {
        this.meetingAgendaKey = meetingAgendaKey;
    }
}
