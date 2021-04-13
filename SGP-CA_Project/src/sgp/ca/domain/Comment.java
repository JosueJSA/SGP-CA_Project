/**
* @author Johann 
* Last modification date format: 31-03-2021
*/

package sgp.ca.domain;

public class Comment {
    
    private int commentKey;
    private String meetingDate;
    private String meetingTime; //Tipo Time en BD
    private String commentDescription;
    private String commentTime; //Otro Time en BD
    private String commentDate;

    public Comment(int commentKey, String meetingDate, String meetingTime, String commentDescription, String commentTime, String commentDate) {
        this.commentKey = commentKey;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.commentDescription = commentDescription;
        this.commentTime = commentTime;
        this.commentDate = commentDate;
    }

    public int getCommentKey() {
        return commentKey;
    }

    public void setCommentKey(int commentKey) {
        this.commentKey = commentKey;
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

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
    
}
