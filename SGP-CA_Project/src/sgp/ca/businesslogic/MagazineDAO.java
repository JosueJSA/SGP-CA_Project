/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Magazine;

/**
 *
 * @author Josue Alarcon
 */
public class MagazineDAO implements IMagazineDAO{
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public void addMagazine(Magazine newMagazine){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "INSERT INTO Magazine VALUES(?,?,?,?);"
            );
            sentenceQuery.setString(1, newMagazine.getMagazineName());
            sentenceQuery.setString(2, newMagazine.getEditorialName());
            sentenceQuery.setString(3, newMagazine.getEditorialCountry());
            sentenceQuery.setInt(4, newMagazine.getIndex());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Magazine.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void deleteMagazineByName(String nameMagazine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMagazine(String oldMagazine, Magazine newMagazine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Magazine getMagazineByName(String name) {
        Magazine magazine = new Magazine();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Magazine WHERE magazineName = ?;"
            );
            sentenceQuery.setString(1, name);
            ResultSet result = sentenceQuery.executeQuery();
            if(result.next()){magazine = new Magazine(
                result.getString("magazineName"), 
                result.getInt("index"), 
                result.getString("editorialName"), 
                result.getString("magazineCountry")
            );}
        }catch(SQLException sqlException){
            Logger.getLogger(Magazine.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return magazine;
        }
    }
    
}
