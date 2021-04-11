/**
* @author Josué Alarcón  
* Last modification date format: 6-04-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;


public class GeneralResume{
    
    private String bodyAcademyKey;
    private String bodyAcademyName; 
    private String ascriptionArea;
    private String ascriptionUnit;
    private String consolidationDegree;
    private String vision;
    private String mission;
    private String generalTarjet;
    private String registrationDate;
    private String lastEvaluation;
    private List<Member> members;
    private List<Lgac> listLgac;
    private List<WorkPlan> workplans;

    public GeneralResume(String bodyAcademyKey, String bodyAcademyName, 
    String ascriptionArea, String ascriptionUnit, String consolidationDegree, 
    String vision, String mission, String generalTarjet, 
    String registrationDate, String lastEvaluation) {
        this.bodyAcademyKey = bodyAcademyKey;
        this.bodyAcademyName = bodyAcademyName;
        this.ascriptionArea = ascriptionArea;
        this.ascriptionUnit = ascriptionUnit;
        this.consolidationDegree = consolidationDegree;
        this.vision = vision;
        this.mission = mission;
        this.generalTarjet = generalTarjet;
        this.registrationDate = registrationDate;
        this.lastEvaluation = lastEvaluation;
        members = new ArrayList<>();
        listLgac = new ArrayList<>();
        workplans = new ArrayList<>();
    }

    
    
    public GeneralResume(){
        members = new ArrayList<>();
        listLgac = new ArrayList<>();
        workplans = new ArrayList<>();
    }
    
    public void addMember(Member newMember){
        members.add(newMember);
    }
    
    public void removeMember(Member member){
        members.remove(member);
    }
    
    public void addLgac(Lgac newLgac){
        listLgac.add(newLgac);
    }
    
    public void removeLgac(Lgac lgac){
        listLgac.remove(lgac);
    }

    public String getBodyAcademyKey(){
        return bodyAcademyKey;
    }

    public void setBodyAcademyKey(String bodyAcademyKey){
        this.bodyAcademyKey = bodyAcademyKey;
    }

    public String getLastEvaluation(){
        return lastEvaluation;
    }

    public void setLastEvaluation(String lastEvaluation){
        this.lastEvaluation = lastEvaluation;
    }
   

    public String getBodyAcademyName(){
        return bodyAcademyName;
    }

    public void setBodyAcademyName(String bodyAcademyName){
        this.bodyAcademyName = bodyAcademyName;
    }

    public String getAscriptionArea(){
        return ascriptionArea;
    }

    public void setAscriptionArea(String ascriptionArea){
        this.ascriptionArea = ascriptionArea;
    }

    public String getAscriptionUnit(){
        return ascriptionUnit;
    }

    public void setAscriptionUnit(String ascriptionUnit){
        this.ascriptionUnit = ascriptionUnit;
    }

    public String getConsolidationDegree(){
        return consolidationDegree;
    }

    public void setConsolidationDegree(String consolidationDegree){
        this.consolidationDegree = consolidationDegree;
    }

    public String getVision(){
        return vision;
    }

    public void setVision(String vision){
        this.vision = vision;
    }

    public String getMission(){
        return mission;
    }

    public void setMission(String mission){
        this.mission = mission;
    }

    public String getGeneralTarjet(){
        return generalTarjet;
    }

    public void setGeneralTarjet(String generalTarjet){
        this.generalTarjet = generalTarjet;
    }

    public String getRegistrationDate(){
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate){
        this.registrationDate = registrationDate;
    }
    
}
