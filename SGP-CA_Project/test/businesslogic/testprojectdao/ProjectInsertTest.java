/**
 *
 * @author johan
 */

package businesslogic.testprojectdao;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;

public class ProjectInsertTest {
    
    public final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private Project project;
    
    @Test
    public void testCorrectProjectInsert(){
        project = new Project(
                "Crecimiento de lenguajes de programación","UV-CA-127", 6, "Activo", 
                "2021-04-07", null, "2021-10-09", "Enfocado en predecir lo que sucedera en los años siguientes"
            );
        PROJECT_DAO.addProject(project);
        Project projectRetrieved = PROJECT_DAO.getProjectbyName("Crecimiento de lenguajes de programación");
        Assert.assertEquals(project.getProjectName(), projectRetrieved.getProjectName());
    }
    
     @Test
    public void testIncorrectProjectInsertionDuplicated(){
        String oldProjectName = "Crecimiento de lenguajes de programación";
        project = new Project(
            "Crecimiento de lenguajes de programación","UV-CA-127", 6, "Activo", 
                "2021-04-07", null, "2021-10-09", "Enfocado en predecir lo que sucedera en los años siguientes"
        );
        PROJECT_DAO.addProject(project);
        Project projectRetrieved = PROJECT_DAO.getProjectbyName("Crecimiento de lenguajes de programación");
        Assert.assertEquals(oldProjectName, projectRetrieved.getProjectName());
    }
    
}
