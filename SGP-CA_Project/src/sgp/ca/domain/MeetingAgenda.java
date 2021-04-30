/**
 * Last modification date format: 23-04-2021
 * @author estef
 */

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;

public class MeetingAgenda {
    private int meetingAgendaKey;
    private String totalTime;
    private String estimatedTotalTime;
    private int totaltopics;
    private List<Topic> topics;
    private List<Prerequisite> prerequisites;

    public MeetingAgenda(int meetingAgendaKey, String totalTime, 
    String estimatedTotalTime) {
        this.meetingAgendaKey = meetingAgendaKey;
        this.totalTime = totalTime;
        this.estimatedTotalTime = estimatedTotalTime;
        this.topics = new ArrayList();
        this.totaltopics = topics.size();
        this.prerequisites = new ArrayList();
    }

    public MeetingAgenda() {
        this.topics = new ArrayList();
        this.totaltopics = topics.size();
        this.prerequisites = new ArrayList();
    }

    public int getMeetingAgendaKey() {
        return meetingAgendaKey;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getEstimatedTotalTime() {
        return estimatedTotalTime;
    }

    public int getTotaltopics() {
        return totaltopics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Prerequisite> getPrerequisites() {
        return prerequisites;
    }

    public void setMeetingAgendaKey(int meetingAgendaKey) {
        this.meetingAgendaKey = meetingAgendaKey;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public void setEstimatedTotalTime(String estimatedTotalTime) {
        this.estimatedTotalTime = estimatedTotalTime;
    }

    public void setTotaltopics(int totaltopics) {
        this.totaltopics = totaltopics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setPrerequisites(List<Prerequisite> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public void addTopic(Topic newtopic){
        topics.add(newtopic);
    }
    
    public void removeTopic(Topic topic){
        topics.remove(topic);
    }
    
    public void addPrerequisite(Prerequisite newprerequisite){
        prerequisites.add(newprerequisite);
    }
    
    public void removePrerequisite(Prerequisite prerequisite){
        prerequisites.remove(prerequisite);
    }
}
