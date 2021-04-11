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

/**
 *
 * @author Josue Alarcon
 */
public class ActionDAO implements IActionDAO{
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public void addActions(Connection connection, Goal goal) {
        goal.getActions().forEach( action -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO Action VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
                );
                sentenceQuery.setInt(1, action.getActionKey());
                sentenceQuery.setInt(2, goal.getGoalIdentifier());
                sentenceQuery.setString(3, action.getStartDate());
                sentenceQuery.setString(4, action.getEndDate());
                sentenceQuery.setString(5, action.getEstimatedEndDate());
                sentenceQuery.setBoolean(6, action.isStatusAction());
                sentenceQuery.setString(7, action.getDescriptionAction());
                sentenceQuery.setString(8, action.getResponsibleAction());
                sentenceQuery.setString(9, action.getResource());
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
            Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return actionList;
        }
    }
    
}
