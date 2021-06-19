/*
* @author Johann
* @versión v1.0
* Last modification date: 17-06-2021
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Project;

public interface IProjectDAO{
    public List<Project> getProjectList();
    public void addProject(Project newProject);
    public List<Project> getProjectListbyName(String nameProject);
    public List<Project> getProjectListbyDate(String dateFilter, String currentDate);
    public Project getProjectbyName(String projectName);
    public boolean updateProject(Project project, String oldProjectName);
    
}