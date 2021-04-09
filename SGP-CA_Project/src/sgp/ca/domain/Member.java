/**
* @author Josué Alarcón  
* Last modification date format: 05-04-2021
*/

package sgp.ca.domain;

public abstract class Member {
    
    private String rfc;
    private String fullName;
    private String emailUV;
    private String curp;
    private String nationality;
    private String dateOfAdmission;
    private String educationalProgram;
    private String cellphone;
    private int staffNumber;
    private String bodyAcademyKey;

    public Member(String rfc, String fullName, String emailUV, 
    String curp, String nationality, String dateOfAdmission, 
    String educationalProgram, int staffNumber, String cellphone, String bodyAcademyKey){
        this.rfc = rfc;
        this.fullName = fullName;
        this.emailUV = emailUV;
        this.curp = curp;
        this.nationality = nationality;
        this.dateOfAdmission = dateOfAdmission;
        this.educationalProgram = educationalProgram;
        this.staffNumber = staffNumber;
        this.cellphone = cellphone;
        this.bodyAcademyKey = bodyAcademyKey;
    }
    
    public Member(){
        
    }

    public String getBodyAcademyKey() {
        return bodyAcademyKey;
    }

    public void setBodyAcademyKey(String bodyAcademyKey) {
        this.bodyAcademyKey = bodyAcademyKey;
    }
    
    public String getRfc(){
        return rfc;
    }

    public void setRfc(String rfc){
        this.rfc = rfc;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getEmailUV(){
        return emailUV;
    }

    public void setEmailUV(String emailUV){
        this.emailUV = emailUV;
    }

    public String getCurp(){
        return curp;
    }

    public void setCurp(String curp){
        this.curp = curp;
    }

    public String getNationality(){
        return nationality;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public String getEducationalProgram(){
        return educationalProgram;
    }

    public void setEducationalProgram(String educationalProgram){
        this.educationalProgram = educationalProgram;
    }

    public int getStaffNumber(){
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber){
        this.staffNumber = staffNumber;
    }

    public String getCellphone(){
        return cellphone;
    }

    public void setCellphone(String cellphone){
        this.cellphone = cellphone;
    }

    public String getDateOfAdmission(){
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission){
        this.dateOfAdmission = dateOfAdmission;
    }
}
