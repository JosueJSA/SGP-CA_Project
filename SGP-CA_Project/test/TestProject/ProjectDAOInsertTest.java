
package TestProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businessLogic.ProjectDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Project;


public class ProjectDAOInsertTest {
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    int cantidadRows;
    
    @Test
    public void addProjectTest() throws SQLException{
        System.out.println("addProject");
        Project project = new Project("Crecimiento","UV-CA-127", 6, "Activo", "2021-04-11", "0000-00-00", "2021-10-09", "Enfocado en predecir lo que sucedera en los años siguientes");
        ProjectDAO instanceProject = new ProjectDAO();
        instanceProject.addProject(project); 
        Statement instructionQuery = query.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select projectName FROM Project");
        queryResult.last();
        cantidadRows = queryResult.getRow();  
        Assert.assertEquals(2, cantidadRows);
    }
   
    
    
}
