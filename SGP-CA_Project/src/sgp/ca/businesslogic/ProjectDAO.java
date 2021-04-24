/**
* @author Johann 
* Last modification date format: 23-03-2021
*/

package sgp.ca.businesslogic;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Project;


public class ProjectDAO implements IProjectDAO{
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    
    @Override
    public List<Project> getProjectList(){
        List<Project> listProjects = new ArrayList<>();
        try{
            Statement instructionQuery = query.getConnectionDatabase().createStatement();;
            ResultSet queryResult = instructionQuery.executeQuery("Select projectName, status, durationProjectInMonths FROM Project");
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"), 
                    queryResult.getString("status"),
                    queryResult.getInt("durationProjectInMonths")));
            }
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
            return listProjects;
        }
    }
    

    @Override
    public void addProject(Project newProject){
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "INSERT INTO Project VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );
            sentenceQuery.setString(1, newProject.getProjectName());
            sentenceQuery.setString(2, newProject.getBodyAcademyKey());
            sentenceQuery.setInt(3, newProject.getDurationProjectInMonths());
            sentenceQuery.setString(4, newProject.getStatus());
            sentenceQuery.setString(5, newProject.getStartDate());
            sentenceQuery.setString(6, newProject.getEndDate());
            sentenceQuery.setString(7, newProject.getEstimatedEndDate());
            sentenceQuery.setString(8, newProject.getDescription());

            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            //Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
    

    @Override
    public List<Project> getProjectListbyName(String projectName) {
        List<Project> listProjects = new ArrayList<>();
        try{
            PreparedStatement instructionQuery = query.getConnectionDatabase().prepareStatement("SELECT projectName, "
                    + "status, durationProjectInMonths FROM Project WHERE projectName = ? ;");
            instructionQuery.setString(1, projectName);
            ResultSet queryResult = instructionQuery.executeQuery();
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"), 
                    queryResult.getString("status"),
                    queryResult.getInt("durationProjectInMonths")));
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            query.closeConnection();
            return listProjects;
        }
    }
    
    

    @Override
    public List<Project> getProjectListbyDate(String dateFilter, String currentDate) {
        List<Project> listProjects = new ArrayList<>();
        try{
            PreparedStatement instructionQuery = query.getConnectionDatabase().prepareStatement("SELECT projectName, "
                    + "status, durationProjectInMonths, startDate FROM Project WHERE startDate BETWEEN ? and ? ;");  
            instructionQuery.setString(1, dateFilter);
            instructionQuery.setString(2, currentDate);
            ResultSet queryResult = instructionQuery.executeQuery();
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"), 
                    queryResult.getString("status"),
                    queryResult.getInt("durationProjectInMonths"),
                    queryResult.getString("startDate")));
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            query.closeConnection();
            return listProjects;
        }
    }
    
    @Override
    public Project getProjectbyName(String projectName) {
        Project project = new Project();
        try{
            PreparedStatement instructionQuery = query.getConnectionDatabase().prepareStatement("SELECT *"
                    + " FROM Project WHERE projectName = ? ;");
            instructionQuery.setString(1, projectName);
            ResultSet queryResult = instructionQuery.executeQuery();
            if(queryResult.next()){
                project = new Project(
                    queryResult.getString("projectName"),
                    queryResult.getString("bodyAcademyKey"),
                    queryResult.getInt("durationProjectInMonths"),
                    queryResult.getString("status"),
                    queryResult.getString("startDate"),
                    queryResult.getString("endDate"),
                    queryResult.getString("estimatedEndDate"),
                    queryResult.getString("description")
                );
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            query.closeConnection();
            return project;
        }
    }
    
    @Override
    public boolean updateProject(Project project, String oldProjectName){
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "UPDATE Project SET projectaName = ?, bodyAcademyKey = ?, durationProjectInMonths = ?, status = ?,"
                + " startDate = ?, endDate = ?, estimatedEndDate = ?, description= ? WHERE projectaName = ?;"
            );
            sentenceQuery.setString(1, project.getProjectName());
            sentenceQuery.setString(2, project.getBodyAcademyKey());
            sentenceQuery.setInt(3, project.getDurationProjectInMonths());
            sentenceQuery.setString(4, project.getStatus());
            sentenceQuery.setString(5, project.getStartDate());
            sentenceQuery.setString(6, project.getEndDate());
            sentenceQuery.setString(7, project.getEstimatedEndDate());
            sentenceQuery.setString(8, project.getDescription());
            sentenceQuery.setString(9, oldProjectName);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, sqlException);
            return false;
        }finally{
            query.closeConnection();
            return true;
        }
    }
}
