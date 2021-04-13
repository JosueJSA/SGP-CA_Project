
package sgp.ca.domain;

public class MeetingNote {
    private String meetingDate;
    private String meetingTime;
    private String note; 

    public MeetingNote(String meetingDate, String meetingTime, String note) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.note = note;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
