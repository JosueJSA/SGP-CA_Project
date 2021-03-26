/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

import java.util.Date;


public class Schooling{
    
    private String studyName, studiesInstitution, levelOfStudy,
            studiesSatate, studiesDiscipline, studyArea;
    private int idProfessionalStudies;
    private Date dateOfObteiningStudies;

    public Schooling(String studyName, String studiesInstitution, 
    String levelOfStudy, String studiesSatate, String studiesDiscipline, 
    String studyArea, int idProfessionalStudies, Date dateOfObteiningStudies){
        this.studyName = studyName;
        this.studiesInstitution = studiesInstitution;
        this.levelOfStudy = levelOfStudy;
        this.studiesSatate = studiesSatate;
        this.studiesDiscipline = studiesDiscipline;
        this.studyArea = studyArea;
        this.idProfessionalStudies = idProfessionalStudies;
        this.dateOfObteiningStudies = dateOfObteiningStudies;
    }

    public String getStudyName(){
        return studyName;
    }

    public void setStudyName(String studyName){
        this.studyName = studyName;
    }

    public String getStudiesInstitution(){
        return studiesInstitution;
    }

    public void setStudiesInstitution(String studiesInstitution){
        this.studiesInstitution = studiesInstitution;
    }

    public String getLevelOfStudy(){
        return levelOfStudy;
    }

    public void setLevelOfStudy(String levelOfStudy){
        this.levelOfStudy = levelOfStudy;
    }

    public String getStudiesSatate(){
        return studiesSatate;
    }

    public void setStudiesSatate(String studiesSatate){
        this.studiesSatate = studiesSatate;
    }

    public String getStudiesDiscipline(){
        return studiesDiscipline;
    }

    public void setStudiesDiscipline(String studiesDiscipline){
        this.studiesDiscipline = studiesDiscipline;
    }

    public String getStudyArea(){
        return studyArea;
    }

    public void setStudyArea(String studyArea){
        this.studyArea = studyArea;
    }

    public int getIdProfessionalStudies(){
        return idProfessionalStudies;
    }

    public void setIdProfessionalStudies(int idProfessionalStudies){
        this.idProfessionalStudies = idProfessionalStudies;
    }

    public Date getDateOfObteiningStudies(){
        return dateOfObteiningStudies;
    }

    public void setDateOfObteiningStudies(Date dateOfObteiningStudies){
        this.dateOfObteiningStudies = dateOfObteiningStudies;
    }   
}
