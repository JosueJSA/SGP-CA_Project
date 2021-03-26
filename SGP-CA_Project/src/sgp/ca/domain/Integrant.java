/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;


public class Integrant extends Member{
    
    private String appointmentMember, participationType;
    private int homePhone, workPhone;

    public Integrant(String rfc, String fullName, String emailUV, String aditionalEmail, 
    String curp, String nationality, int staffNumber, int cellphone, String dateOfAdmission, 
    String appointmentMember, String participationType, int homePhone, int workPhone){
        super(rfc, fullName, emailUV, aditionalEmail, curp, nationality, staffNumber, cellphone, dateOfAdmission);
        this.appointmentMember = appointmentMember;
        this.participationType = participationType;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
    }
    
    public Integrant(){
        
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

    public int getHomePhone(){
        return homePhone;
    }

    public void setHomePhone(int homePhone){
        this.homePhone = homePhone;
    }

    public int getWorkPhone(){
        return workPhone;
    }

    public void setWorkPhone(int workPhone){
        this.workPhone = workPhone;
    }
    
    
}
