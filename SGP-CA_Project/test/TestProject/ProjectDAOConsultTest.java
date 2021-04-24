
package TestProject;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;

public class ProjectDAOConsultTest {
    @Test
    public void  getProjectListTest() throws SQLException{
        List<Project> listProjects = new ArrayList<>();
        System.out.println(" getProjectList");
        ProjectDAO instanceProjectList = new ProjectDAO(); 
        listProjects = instanceProjectList.getProjectList();
        Assert.assertTrue(!listProjects.isEmpty());
    }
    
    
    @Test
    public void getProjectListbyNameEquals(){
        List<Project> listProjectsbyName = new ArrayList<>();
        boolean flag = false;
        System.out.println("getProjectListbyNameEquals");
        ProjectDAO instanceProjectListbyName = new ProjectDAO(); 
        listProjectsbyName = instanceProjectListbyName.getProjectListbyName("Crecimiento de lenguajes de programación");
            if ("Crecimiento de lenguajes de programación".equals(listProjectsbyName.get(0).getProjectName())){
                flag = true;
            }
        Assert.assertTrue(flag);
    }
    
    @Test
    public void getProjectListbyNameEmpty(){
        List<Project> listProjectsbyName = new ArrayList<>();
        System.out.println("getProjectListbyNameEmpty");
        ProjectDAO instanceProjectListbyName = new ProjectDAO(); 
        listProjectsbyName = instanceProjectListbyName.getProjectListbyName("Crecimiento de lenguajes de programación");
        Assert.assertTrue(!listProjectsbyName.isEmpty());
    }
    
    @Test
    public void getProjectListbyDateEquals(){
        List<Project> listProjectsbyDate = new ArrayList<>();
        boolean flag = true;
        System.out.println("getProjectListbyNameEquals");
        ProjectDAO instanceProjectListbyDate= new ProjectDAO(); 
        listProjectsbyDate = instanceProjectListbyDate.getProjectListbyDate("2021-04-07", "2021-04-28");
        System.out.println(listProjectsbyDate.size());
        System.out.println(listProjectsbyDate.get(0).getStartDate());
        System.out.println(listProjectsbyDate.get(1).getStartDate());
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
            Date date1 = dateFormat.parse("2021-04-07");
            for (int i=0; i < listProjectsbyDate.size(); i++){
                String dateDataBase = listProjectsbyDate.get(0).getStartDate();
                Date date2 = dateFormat.parse(dateDataBase);
                if (!date1.before(date2)){
                    flag = false;
                }
            } 
        }catch(ParseException ex){
           // Logger.getLogger(ProjectDAOConsultTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        Assert.assertTrue(flag);
    }
    
    
    @Test
    public void getProjectListbyDateEmpty(){
        List<Project> listProjectsbyDate = new ArrayList<>();
        boolean flag = true;
        System.out.println("getProjectListbyNameEquals");
        ProjectDAO instanceProjectListbyDate= new ProjectDAO(); 
        listProjectsbyDate = instanceProjectListbyDate.getProjectListbyDate("2021-04-07", "2021-04-28");
        Assert.assertTrue(!listProjectsbyDate.isEmpty());
    }
    
    @Test
    public void  getProjectbyNameTest() throws SQLException{
        Project project = new Project();
        System.out.println(" getProjectbyName");
        ProjectDAO instanceProjectbyName = new ProjectDAO(); 
        project = instanceProjectbyName.getProjectbyName("Crecimiento de lenguajes de programación");
        Assert.assertNotNull(project);
    }
    
    @Test
    public void getProjectbyNameEquals(){
        Project projectbyName = new Project();
        boolean flag = false;
        System.out.println("getProjectbyNameEquals");
        ProjectDAO instanceProjectbyName = new ProjectDAO(); 
        projectbyName = instanceProjectbyName.getProjectbyName("Crecimiento de lenguajes de programación");
            if ("Crecimiento de lenguajes de programación".equals(projectbyName.getProjectName())){
                flag = true;
            }
        Assert.assertTrue(flag);
    }
}
