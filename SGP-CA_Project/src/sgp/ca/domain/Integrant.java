/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;


public class Integrant extends Member{
    
    private String appointmentMember;
    private String participationType;
    private String aditionalMail;
    private String homePhone;
    private String workPhone;
    private List<Schooling> schooling;

    public Integrant(String rfc,String fullName, String emailUV, String curp, 
    String nationality, String dateOfAdmission, String educationalProgram, 
    int staffNumber, String cellphone, String bodyAcademyKey, String appointmentMember, 
    String participationType, String aditionalMail, String homePhone, String workPhone){
        super(
            rfc, fullName, emailUV, curp, nationality, dateOfAdmission, 
            educationalProgram, staffNumber, cellphone, bodyAcademyKey
        );
        this.appointmentMember = appointmentMember;
        this.participationType = participationType;
        this.aditionalMail = aditionalMail;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.schooling = new ArrayList<>();
    }

    public Integrant(){
        this.schooling = new ArrayList<>();
    }

    public List<Schooling> getSchooling() {
        return schooling;
    }

    
    public void addSchooling(Schooling newSchooling){
        this.schooling.add(newSchooling);
    }
    
    public void removeSchooling(Schooling schooling){
        this.schooling.remove(schooling);
    }

    public void setSchooling(List<Schooling> schooling) {
        this.schooling = schooling;
    }  
    

    public String getAppointmentMember(){
        return appointmentMember;
    }

    public void setAppointmentMember(String appointmentMember){
        this.appointmentMember = appointmentMember;
    }

    public String getParticipationType(){
        return participationType;
    }

    public void setParticipationType(String participationType){
        this.participationType = participationType;
    }

    public String getAditionalMail(){
        return aditionalMail;
    }

    public void setAditionalMail(String aditionalMail){
        this.aditionalMail = aditionalMail;
    }

    public String getHomePhone(){
        return homePhone;
    }

    public void setHomePhone(String homePhone){
        this.homePhone = homePhone;
    }

    public String getWorkPhone(){
        return workPhone;
    }

    public void setWorkPhone(String workPhone){
        this.workPhone = workPhone;
    }
    
}
