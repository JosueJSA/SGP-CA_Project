/**
* @author Johann 
* Last modification date format: 23-03-2021
*/

package sgp.ca.businessLogic;

import domain.Project;
import java.util.List;

public interface IProjectDAO{
    public List<Project> getProjectList();
    public void addProject(Project newProject);
    public List<Project> getProjectListbyName(String nameProject);
    public List<Project> getProjectListbyDate(String dateFilter, String currentDate);
    
}