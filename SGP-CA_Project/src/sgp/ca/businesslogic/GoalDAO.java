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
import sgp.ca.domain.Goal;
import sgp.ca.domain.WorkPlan;

/**
 *
 * @author Josue Alarcon
 */
public class GoalDAO implements IGoalDAO{
    
    private ActionDAO actionDAO = new ActionDAO();
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public void addGoals(Connection connection, WorkPlan workPlan){
        workPlan.getGoals().forEach( goal -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO Goal VALUES (?, ?, ?, ?, ?, ?);"
                );
                sentenceQuery.setInt(1, goal.getGoalIdentifier());
                sentenceQuery.setInt(2, workPlan.getWorkplanKey());
                sentenceQuery.setString(3, goal.getStartDate());
                sentenceQuery.setString(4, goal.getEndDate());
                sentenceQuery.setBoolean(5, goal.isStatusGoal());
                sentenceQuery.setString(6, goal.getDescription());
                sentenceQuery.executeUpdate();
                actionDAO.addActions(connection, goal);
            }catch(SQLException sqlException){
                try {
                    connection.rollback();
                    Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, sqlException);
                } catch (SQLException ex) {
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public List<Goal> getGoalListByWorkPlan(int getWorkplanKey) {
        List<Goal> goalList = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Goal WHERE workplanKey = ?;"
            );
            sentenceQuery.setInt(1, getWorkplanKey);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){goalList.add(new Goal(
                queryResult.getInt("goalIdentifier"),
                queryResult.getDate("startDate").toString(),
                queryResult.getDate("endDate").toString(),
                queryResult.getBoolean("statusGoal"),
                queryResult.getString("descriptionGoal")
            ));}
        }catch(SQLException sqlException){
            Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return goalList;
        }
    }

    @Override
    public void deleteActions(Connection connection, List<Goal> goals) {
        goals.forEach( goal -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "DELETE FROM Action WHERE goalIdentifier = ?;"
                );
                sentenceQuery.setInt(1, goal.getGoalIdentifier());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try {
                    connection.rollback();
                    Logger.getLogger(Goal.class.getName()).log(Level.SEVERE, null, sqlException);
                } catch (SQLException ex) {
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
}
