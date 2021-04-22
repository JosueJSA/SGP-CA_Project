/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package sgp.ca.domain;


public class Collaborator extends Member{
    
    private String studyArea;
    private String nameBACollaborator;
    private String highestDegreeStudies;

    public Collaborator(String rfc, String fullName, String emailUV, String participationStatus, 
    String curp, String nationality, String dateOfAdmission, 
    String educationalProgram, int staffNumber, String cellphone, String bodyAcademyKey, String studyArea, 
    String nameBACollaborator, String highestDegreeStudies){
        super(
            rfc, fullName, emailUV, participationStatus, curp, nationality, dateOfAdmission, 
            educationalProgram, staffNumber, cellphone, bodyAcademyKey
        );
        this.studyArea = studyArea;
        this.nameBACollaborator = nameBACollaborator;
        this.highestDegreeStudies = highestDegreeStudies;
    }
    
    public Collaborator(){
        
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
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if(super.getRfc().equals(((Collaborator)obj).getRfc())){
            isEqual = true;
        }
        return isEqual;
    }
}
