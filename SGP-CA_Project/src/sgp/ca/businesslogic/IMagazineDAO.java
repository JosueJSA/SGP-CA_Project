/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import sgp.ca.domain.Magazine;

/**
 *
 * @author Josue Alarcon
 */
public interface IMagazineDAO {
    
    public Magazine getMagazineByName(String name);
    public void addMagazine(Magazine newMagazine);
    public void deleteMagazineByName(String nameMagazine);
    public void updateMagazine(String oldMagazine, Magazine newMagazine);
    
}
