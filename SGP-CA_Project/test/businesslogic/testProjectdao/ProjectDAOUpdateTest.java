
package businesslogic.testProjectdao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;

public class ProjectDAOUpdateTest {
  
    @Test
    public void updateProjectTest(){
        System.out.println("updateProject");
        Project project = new Project("Crecimiento","UV-CA-127", 6, "Activo", 
                "2021-04-11", "0000-00-00", "2021-10-09", "Enfocado...");
        ProjectDAO instanceProject = new ProjectDAO();
        Assert.assertTrue(instanceProject.updateProject(project, "Crecimiento de lenguajes de programación"));
    }
}
