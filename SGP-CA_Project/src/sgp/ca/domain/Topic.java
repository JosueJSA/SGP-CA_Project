/**
 * Last modification date format: 23-04-2021
 * @author estef
 */

package sgp.ca.domain;

public class Topic {
    private int numberTopic;
    private int meetingAgenda;
    private String startTime;
    private String endTime;
    private String plannedTime;
    private String realTime;
    private String descriptionTopic;
    private String discissionLeader;

    public Topic(int numberTopic, int meetingAgenda, String startTime,
    String endTime, String plannedTime, String realTime, String descriptionTopic,
    String discissionLeader) {
        this.numberTopic = numberTopic;
        this.meetingAgenda = meetingAgenda;
        this.startTime = startTime;
        this.endTime = endTime;
        this.plannedTime = plannedTime;
        this.realTime = realTime;
        this.descriptionTopic = descriptionTopic;
        this.discissionLeader = discissionLeader;
    }

    public Topic() {
    }

    public int getNumberTopic() {
        return numberTopic;
    }

    public int getMeetingAgenda() {
        return meetingAgenda;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public String getDescriptionTopic() {
        return descriptionTopic;
    }

    public String getDiscissionLeader() {
        return discissionLeader;
    }

    public void setNumberTopic(int numberTopic) {
        this.numberTopic = numberTopic;
    }

    public void setMeetingAgenda(int meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public void setDescriptionTopic(String descriptionTopic) {
        this.descriptionTopic = descriptionTopic;
    }

    public void setDiscissionLeader(String discissionLeader) {
        this.discissionLeader = discissionLeader;
    }
}
