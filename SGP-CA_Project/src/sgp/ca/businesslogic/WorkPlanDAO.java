/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.WorkPlan;


public class WorkPlanDAO implements IWorkPlanDAO{

    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    private GoalDAO goalDAO = new GoalDAO();
    
    @Override
    public WorkPlan getWorkPlan(String endDatePlan){
        WorkPlan workPlan = new WorkPlan();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM WorkPlan WHERE endDate = ?;"
            );
            sentenceQuery.setString(1, endDatePlan);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){workPlan = new WorkPlan(
                queryResult.getInt("workplanKey"),
                queryResult.getInt("durationInYears"),
                queryResult.getString("generalTarjet"),
                queryResult.getDate("startDate").toString(),
                queryResult.getDate("endDate").toString(),
                queryResult.getString("bodyAcademyKey")
            );}
            workPlan.setGoals(goalDAO.getGoalListByWorkPlan(workPlan.getWorkplanKey()));
        }catch(SQLException sqlException){
            Logger.getLogger(WorkPlan.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return workPlan;
        }
    }

    @Override
    public void addWorkPlan(WorkPlan newWorkPlan){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "INSERT INTO WorkPlan VALUES(?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setInt(1, newWorkPlan.getWorkplanKey());
            sentenceQuery.setString(2, newWorkPlan.getBodyAcademyKey());
            sentenceQuery.setString(3, newWorkPlan.getStartDatePlan());
            sentenceQuery.setString(4, newWorkPlan.getEndDatePlan());
            sentenceQuery.setString(5, newWorkPlan.getGeneralTarget());
            sentenceQuery.setInt(6, newWorkPlan.getDurationInYears());
            sentenceQuery.executeUpdate();
            goalDAO.addGoals(connection, newWorkPlan);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(WorkPlan.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updateWorkPlan(WorkPlan workPlan,  WorkPlan oldWorkPlan){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteGoals(connection, oldWorkPlan);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "UPDATE WorkPlan SET workplanKey = ?, bodyAcademyKey = ?, startDate = ?, endDate = ?, generalTarjet = ?, durationInYears = ? WHERE workplanKey = ?;"
            );
            sentenceQuery.setInt(1, workPlan.getWorkplanKey());
            sentenceQuery.setString(2, workPlan.getBodyAcademyKey());
            sentenceQuery.setString(3, workPlan.getStartDatePlan());
            sentenceQuery.setString(4, workPlan.getEndDatePlan());
            sentenceQuery.setString(5, workPlan.getGeneralTarget());
            sentenceQuery.setInt(6, workPlan.getDurationInYears());
            sentenceQuery.setInt(7, oldWorkPlan.getWorkplanKey());
            sentenceQuery.executeUpdate();
            goalDAO.addGoals(connection, workPlan);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(WorkPlan.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void deleteWorkPlan(WorkPlan workPlan){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteGoals(connection, workPlan);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM WorkPlan WHERE workplanKey = ?;"
            );
            sentenceQuery.setInt(1, workPlan.getWorkplanKey());
            sentenceQuery.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try {
                connection.rollback();
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            } catch (SQLException ex) {
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }    
    }
    
    @Override
    public void deleteGoals(Connection connection, WorkPlan workPlan){
        try{
            goalDAO.deleteActions(connection, workPlan.getGoals());
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Goal WHERE workplanKey = ?;"
            );
            sentenceQuery.setInt(1, workPlan.getWorkplanKey());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(WorkPlanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
