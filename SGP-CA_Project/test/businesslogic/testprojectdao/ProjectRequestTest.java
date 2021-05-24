/**
 *
 * @author johan
 */
package businesslogic.testprojectdao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;


public class ProjectRequestTest {
    
   private final ProjectInitializer PROJECT_INITIALIZER = new ProjectInitializer();
   public final ProjectDAO PROJECT_DAO = new ProjectDAO();
    
    @Test
    public void testGetExistProjectByUrlFile(){
        PROJECT_INITIALIZER.prepareProjectInsertionForTest();
        Project projectRetrieved = PROJECT_DAO.getProjectbyName("Crecimiento de lenguajes de programación");
        String supposedProject = "Crecimiento de lenguajes de programación";
        Assert.assertEquals(supposedProject, projectRetrieved.getProjectName());
    }
    
    @Test
    public void testGetNotExistReceptionWorKByUrlFile(){
        Project projectRetrieved = PROJECT_DAO.getProjectbyName("Crecimiento de lenguajes de programación");
        Assert.assertNull(projectRetrieved.getProjectName());
    }
}
