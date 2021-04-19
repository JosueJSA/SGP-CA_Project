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
    String password = null;

    public Integrant(String rfc,String fullName, String emailUV, String password,
    String curp, String nationality, String dateOfAdmission, String educationalProgram, 
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
        this.password = password;
        this.schooling = new ArrayList<>();
    }

    public Integrant(){
        this.schooling = new ArrayList<>();
    }

    public Integrant(String sagA8906245M7, String angel_Juan_Sánchez_García, String angelsanchezuvmx, String sagA890624HVZNRN09, String mexicano, String string, String licenciatura_en_Ingeniería_de_Software, int i, String string0, String uvcA127, String ptc, String integrante, String angelsg89hotmailcom, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    @Override
    public int hashCode(){
        return super.getEmailUV().hashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        Integrant integrant = (Integrant)obj;
        if((super.getRfc().equals(integrant.getRfc())) && (this.schooling.size() == integrant.getSchooling().size())){
            isEqual = true;
        }
        return isEqual;
    }
    
}
