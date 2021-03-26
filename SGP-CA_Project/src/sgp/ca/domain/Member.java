/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

public abstract class Member {
    
    private String rfc, fullName, emailUV, aditionalEmail, curp, nationality, dateOfAdmission;
    private int staffNumber, cellphone;

    public Member(String rfc, String fullName, String emailUV, String aditionalEmail, 
    String curp, String nationality, int staffNumber, int cellphone, String dateOfAdmission){
        this.rfc = rfc;
        this.fullName = fullName;
        this.emailUV = emailUV;
        this.aditionalEmail = aditionalEmail;
        this.curp = curp;
        this.nationality = nationality;
        this.staffNumber = staffNumber;
        this.cellphone = cellphone;
        this.dateOfAdmission = dateOfAdmission;
    }
    
    public Member(){
        
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

    public String getAditionalEmail(){
        return aditionalEmail;
    }

    public void setAditionalEmail(String aditionalEmail){
        this.aditionalEmail = aditionalEmail;
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

    public int getStaffNumber(){
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber){
        this.staffNumber = staffNumber;
    }

    public int getCellphone(){
        return cellphone;
    }

    public void setCellphone(int cellphone){
        this.cellphone = cellphone;
    }

    public String getDateOfAdmission(){
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission){
        this.dateOfAdmission = dateOfAdmission;
    }
}
