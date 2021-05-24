/**
 *
 * @author johan
 */
package businesslogic.testprojectdao;

import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;

public class ProjectInitializer {
    
    public final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private Project project;
    
    public void prepareProjectInsertionForTest(){
        if(PROJECT_DAO.getProjectListbyName("Crecimiento de lenguajes de programación") == null){
             project = new Project(
                "Crecimiento de lenguajes de programación","UV-CA-127", 6, "Activo", 
                "2021-04-07", null, "2021-10-09", "Enfocado en predecir lo que sucedera en los años siguientes"
            );
        PROJECT_DAO.addProject(project);
        }
    }
    
}
