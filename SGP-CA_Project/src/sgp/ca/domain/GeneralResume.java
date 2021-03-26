/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GeneralResume{
    
    private String bodyAcademyKey, bodyAcademyName, ascriptionArea, ascriptionUnit, 
                   consolidationDegree, vision, mission, educationalProgram, generalTarjet;
    private Date registrationDate;
    private List<Member> members = new ArrayList<>();
    private List<Lgac> listLgac = new ArrayList<>();

    public GeneralResume(String bodyAcademyKey, String bodyAcademyName, String ascriptionArea, 
    String ascriptionUnit, String consolidationDegree, String vision, String mission,
    String educationalProgram, String generalTarjet, Date registrationDate){
        this.bodyAcademyKey = bodyAcademyKey;
        this.bodyAcademyName = bodyAcademyName;
        this.ascriptionArea = ascriptionArea;
        this.ascriptionUnit = ascriptionUnit;
        this.consolidationDegree = consolidationDegree;
        this.vision = vision;
        this.mission = mission;
        this.educationalProgram = educationalProgram;
        this.generalTarjet = generalTarjet;
        this.registrationDate = registrationDate;
    }
    
    public GeneralResume(){
        
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

    public String getEducationalProgram(){
        return educationalProgram;
    }

    public void setEducationalProgram(String educationalProgram){
        this.educationalProgram = educationalProgram;
    }

    public String getGeneralTarjet(){
        return generalTarjet;
    }

    public void setGeneralTarjet(String generalTarjet){
        this.generalTarjet = generalTarjet;
    }

    public Date getRegistrationDate(){
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate){
        this.registrationDate = registrationDate;
    }
    
    @Override
    public String toString(){
        String txt = "Key: " + bodyAcademyKey + "\nName: " + bodyAcademyName + "\nArea: " + ascriptionArea
        + "\nUnidad: " + ascriptionUnit + "\nGrado consolidacion: " + consolidationDegree + "\nVision: " + vision + "\nmision: " + mission
        + "\nprograma educativo: " + educationalProgram + "\nobjetivo general: " + generalTarjet + "\nFecha: " + registrationDate;   
        return txt;
    }
}
