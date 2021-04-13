/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package sgp.ca.domain;


public class Project {

    private String projectName;
    private String bodyAcademyKey;
    private String status;
    private String startDate;
    private String endDate;
    private String estimatedEndDate;
    private String description;
    private int durationProjectInMonths;
    

    public Project(String projectName, String bodyAcademyKey, int durationProjectInMonths, String status, String startDate, String endDate, String estimatedEndDate, String description) {
        this.projectName = projectName;
        this.bodyAcademyKey = bodyAcademyKey;
        this.durationProjectInMonths = durationProjectInMonths;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedEndDate = estimatedEndDate;
        this.description = description;
    }
    
   
    public Project(String projectName, String status, int durationProjectInMonths) {
        this.projectName = projectName;
        this.status = status;
        this.durationProjectInMonths = durationProjectInMonths;
    }

    public Project(String projectName, String status, int durationProjectInMonths, String startDate) {
        this.projectName = projectName;
        this.status = status;
        this.durationProjectInMonths = durationProjectInMonths;
        this.startDate = startDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBodyAcademyKey() {
        return bodyAcademyKey;
    }

    public void setBodyAcademyKey(String bodyAcademyKey) {
        this.bodyAcademyKey = bodyAcademyKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(String estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationProjectInMonths() {
        return durationProjectInMonths;
    }

    public void setDurationProjectInMonths(int durationProjectInMonths) {
        this.durationProjectInMonths = durationProjectInMonths;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
