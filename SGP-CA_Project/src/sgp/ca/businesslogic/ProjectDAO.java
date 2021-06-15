/**
* @author Johann 
* Last modification date format: 23-03-2021
*/

package sgp.ca.businesslogic;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.demodao.ValidatorForm;
import sgp.ca.domain.Project;


public class ProjectDAO implements IProjectDAO{
    
    private final ConnectionDatabase QUERY = new ConnectionDatabase();
    
    @Override
    public List<Project> getProjectList(){
        List<Project> listProjects = new ArrayList<>();
        try{
            Statement instructionQuery = QUERY.getConnectionDatabase().createStatement();;
            ResultSet queryResult = instructionQuery.executeQuery("Select projectName, durationProjectInMonths, status, startDate, endDate FROM Project");
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"),
                    queryResult.getInt("durationProjectInMonths"),
                    queryResult.getString("status"),
                    queryResult.getString("startDate"),
                    queryResult.getString(ValidatorForm.convertSQLDateToJavaDate("endDate")))); 
            }
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            QUERY.closeConnection();
            return listProjects;
        }
    }
    
    public List<String> getProjectNameListForEvidence(){
        List<String> projectNamesList = new ArrayList<>();
        try{
            Statement instructionQuery = QUERY.getConnectionDatabase().createStatement();
            ResultSet queryResult = instructionQuery.executeQuery("Select projectName FROM Project");
            while(queryResult.next()){
                String projectName = queryResult.getString("projectName");
                projectNamesList.add(projectName);
            }
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            QUERY.closeConnection();
            return projectNamesList;
        }
    }
    

    @Override
    public void addProject(Project newProject){
        Connection connection = QUERY.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = QUERY.getConnectionDatabase().prepareStatement(
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
            this.insertIntoLgacProject(connection, newProject);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlEsception){
            try{
                connection.rollback();
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlEsception);
            }catch(SQLException ex){
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            QUERY.closeConnection();
        }
    }

    @Override
    public List<Project> getProjectListbyName(String projectName) {
        List<Project> listProjects = new ArrayList<>();
        try{
            PreparedStatement instructionQuery = QUERY.getConnectionDatabase().prepareStatement("SELECT projectName, "
                    + "durationProjectInMonths, status, startDate, endDate FROM Project WHERE projectName = ? ;");
            instructionQuery.setString(1, projectName);
            ResultSet queryResult = instructionQuery.executeQuery();
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"),
                    queryResult.getInt("durationProjectInMonths"),
                    queryResult.getString("status"),
                    queryResult.getString("startDate"),
                    queryResult.getString("endDate")));
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            QUERY.closeConnection();
            return listProjects;
        }
    }
    
    

    @Override
    public List<Project> getProjectListbyDate(String dateFilter, String currentDate) {
        List<Project> listProjects = new ArrayList<>();
        try{
            PreparedStatement instructionQuery = QUERY.getConnectionDatabase().prepareStatement("SELECT projectName, "
                    + "durationProjectInMonths, status, startDate, endDate FROM Project WHERE startDate BETWEEN ? and ? ;");  
            instructionQuery.setString(1, dateFilter);
            instructionQuery.setString(2, currentDate);
            ResultSet queryResult = instructionQuery.executeQuery();
            while(queryResult.next()){
                listProjects.add(new Project(
                    queryResult.getString("projectName"),
                    queryResult.getInt("durationProjectInMonths"),
                    queryResult.getString("status"),
                    queryResult.getString("startDate"),
                    queryResult.getString("endDate")));
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            QUERY.closeConnection();
            return listProjects;
        }
    }
    
    @Override
    public Project getProjectbyName(String projectName) {
        Project project = new Project();
        try{
            PreparedStatement instructionQuery = QUERY.getConnectionDatabase().prepareStatement("SELECT *"
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
            QUERY.closeConnection();
            return project;
        }
    }
    
    @Override
    public boolean updateProject(Project project, String oldProjectName){
        boolean check = false;
        Connection connection = QUERY.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = QUERY.getConnectionDatabase().prepareStatement(
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
            this.insertIntoLgacProject(connection, project);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            QUERY.closeConnection();
            check = true;
            return check;
        }
    }
    
    public void insertIntoLgacProject(Connection connection, Project project){
        project.getLgacs().forEach( lgac -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO IntegratnReceptionWork (rfc, urlFile) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, lgac.getIdentifierLgac() );
                sentenceQuery.setString(2, project.getProjectName());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
