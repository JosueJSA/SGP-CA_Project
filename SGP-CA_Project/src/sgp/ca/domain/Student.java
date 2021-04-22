/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.domain;

public class Student {
    private String nameStudent;
    private String studyDegree;

    public Student(String nameStudent, String studyDegree) {
        this.nameStudent = nameStudent;
        this.studyDegree = studyDegree;
    }
    
    public Student(){
        this.nameStudent = "";
        this.nameStudent = "";
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getStudyDegree() {
        return studyDegree;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public void setStudyDegree(String studyDegree) {
        this.studyDegree = studyDegree;
    }
    
    
}
