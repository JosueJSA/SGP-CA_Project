/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Action;
import sgp.ca.domain.Goal;
import sgp.ca.domain.WorkPlan;

/**
 *
 * @author Josue Alarcon
 */
public class GoalDAO implements IGoalDAO{
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public boolean addGoals(Connection connection, WorkPlan workPlan){
        boolean correctInsertion = false;
        for(Goal goal : workPlan.getGoals()){
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO Goal (workplanKey, startDate, endDate, statusGoal, descriptionGoal) VALUES (?, ?, ?, ?, ?);",
                    PreparedStatement.RETURN_GENERATED_KEYS
                );
                sentenceQuery.setInt(1, workPlan.getWorkplanKey());
                sentenceQuery.setString(2, goal.getStartDate());
                sentenceQuery.setString(3, goal.getEndDate());
                sentenceQuery.setBoolean(4, goal.isStatusGoal());
                sentenceQuery.setString(5, goal.getDescription());
                sentenceQuery.executeUpdate();
                this.updateGoalWithKeyGenerated(sentenceQuery, goal);
                this.addActions(connection, goal);
                correctInsertion = true;
            }catch(SQLException sqlException){
                try{
                    correctInsertion = false;
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return correctInsertion;
    }

    @Override
    public List<Goal> getGoalListByWorkPlan(int getWorkplanKey){
        List<Goal> goalList = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Goal WHERE workplanKey = ?;"
            );
            sentenceQuery.setInt(1, getWorkplanKey);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){
                Goal newGoal = new Goal(
                    queryResult.getInt("goalIdentifier"),
                    queryResult.getDate("startDate").toString(),
                    queryResult.getDate("endDate").toString(),
                    queryResult.getBoolean("statusGoal"),
                    queryResult.getString("descriptionGoal")
                );
                newGoal.setActions(this.getActionsByGoal(newGoal.getGoalIdentifier()));
                goalList.add(newGoal);
            }
        }catch(SQLException sqlException){
            goalList = null;
            Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return goalList;
        }
    }

    @Override
    public boolean deleteActions(Connection connection, List<Goal> goals){
        boolean correctDelete = false;
        for(Goal goal : goals){
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "DELETE FROM Action WHERE goalIdentifier = ?;"
                );
                sentenceQuery.setInt(1, goal.getGoalIdentifier());
                sentenceQuery.executeUpdate();
                correctDelete = true;
            }catch(SQLException sqlException){
                try {
                    correctDelete = false;
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, sqlException);
                } catch (SQLException ex) {
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return correctDelete;
    }
    
    @Override
    public boolean addActions(Connection connection, Goal goal) {
        boolean correctInsertion = false;
        for(Action action : goal.getActions()){
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO Action (goalIdentifier, startDateAction, endDateAction,"
                    + " estimatedEndDate, statusAction, descriptionAction, responsibleAction, "
                    + " resourse) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
                );
                sentenceQuery.setInt(1, goal.getGoalIdentifier());
                sentenceQuery.setString(2, action.getStartDate());
                sentenceQuery.setString(3, action.getEndDate());
                sentenceQuery.setString(4, action.getEstimatedEndDate());
                sentenceQuery.setBoolean(5, action.isStatusAction());
                sentenceQuery.setString(6, action.getDescriptionAction());
                sentenceQuery.setString(7, action.getResponsibleAction());
                sentenceQuery.setString(8, action.getResource());
                sentenceQuery.executeUpdate();
                correctInsertion = true;
            }catch(SQLException sqlException){
                try {
                    correctInsertion = false;
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, sqlException);
                } catch (SQLException ex) {
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return correctInsertion;
    }

    @Override
    public List<Action> getActionsByGoal(int goalIdentifier) {
        List<Action> actionList = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Action WHERE goalIdentifier = ?;"
            );
            sentenceQuery.setInt(1, goalIdentifier);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){actionList.add(new Action(
                queryResult.getInt("actionKey"),
                queryResult.getDate("startDateAction").toString(),
                queryResult.getDate("endDateAction").toString(),
                queryResult.getDate("estimatedEndDate").toString(),
                queryResult.getString("descriptionAction"),
                queryResult.getString("responsibleAction"),    
                queryResult.getString("resourse"), 
                queryResult.getBoolean("statusAction")
            ));}
        }catch(SQLException sqlException){
            Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return actionList;
        }
    }

    public void updateGoalWithKeyGenerated(PreparedStatement statement, Goal goal){
        try{
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                goal.setGoalIdentifier(result.getInt(1));
            }
        }catch(SQLException ex){
            Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
