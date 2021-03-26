/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;


public class Collaborator extends Member{
    
    private String studyArea, nameBACollaborator, highestDegreeStudies;

    public Collaborator(String studyArea, String nameBACollaborator, String highestDegreeStudies, 
    String rfc, String fullName, String emailUV, String aditionalEmail, 
    String curp, String nationality, int staffNumber, int cellphone, String dateOfAdmission){
        super(rfc, fullName, emailUV, aditionalEmail, curp, nationality, staffNumber, cellphone, dateOfAdmission);
        this.studyArea = studyArea;
        this.nameBACollaborator = nameBACollaborator;
        this.highestDegreeStudies = highestDegreeStudies;
    }

    public String getStudyArea(){
        return studyArea;
    }

    public void setStudyArea(String studyArea){
        this.studyArea = studyArea;
    }

    public String getNameBACollaborator(){
        return nameBACollaborator;
    }

    public void setNameBACollaborator(String nameBACollaborator){
        this.nameBACollaborator = nameBACollaborator;
    }

    public String getHighestDegreeStudies(){
        return highestDegreeStudies;
    }

    public void setHighestDegreeStudies(String highestDegreeStudies){
        this.highestDegreeStudies = highestDegreeStudies;
    }
}
