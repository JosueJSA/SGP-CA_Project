
package domain;


public class MeetingPending {
    private String meetingDate;
    private String meetingTime;
    private String pending; 

    public MeetingPending(String meetingDate, String meetingTime, String pending) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.pending = pending;
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

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }
    
}
