/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;


public class Schooling{
    
    private String studyName;
    private String studiesInstitution;
    private String levelOfStudy;
    private String studiesSatate;
    private String studiesDiscipline;
    private String studyArea;
    private String dateOfObteiningStudies;
    private String idProfessionalStudies;

    public Schooling(String studyName, String studiesInstitution, 
    String levelOfStudy, String studiesSatate, String studiesDiscipline, 
    String studyArea, String dateOfObteiningStudies, String idProfessionalStudies){
        this.studyName = studyName;
        this.studiesInstitution = studiesInstitution;
        this.levelOfStudy = levelOfStudy;
        this.studiesSatate = studiesSatate;
        this.studiesDiscipline = studiesDiscipline;
        this.studyArea = studyArea;
        this.dateOfObteiningStudies = dateOfObteiningStudies;
        this.idProfessionalStudies = idProfessionalStudies;
    }
    
    public Schooling(){
        
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

    public String getIdProfessionalStudies(){
        return idProfessionalStudies;
    }

    public void setIdProfessionalStudies(String idProfessionalStudies){
        this.idProfessionalStudies = idProfessionalStudies;
    }

    public String getDateOfObteiningStudies(){
        return dateOfObteiningStudies;
    }

    public void setDateOfObteiningStudies(String dateOfObteiningStudies){
        this.dateOfObteiningStudies = dateOfObteiningStudies;
    }  
}
