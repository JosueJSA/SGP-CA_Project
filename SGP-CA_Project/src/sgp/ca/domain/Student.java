
package sgp.ca.domain;

/**
 *
 * @author estef
 */
public class Student {
    private String nameStudent, studyDegree;

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
