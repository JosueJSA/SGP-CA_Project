/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Integrant;

/**
 *
 * @author josue
 */
public interface IIntegrantDAO {
    
    public Integrant getIntegrantTocken(Integrant usuario);
    public Integrant getIntegrantToken(String email, String password);
    public List<Integrant> getMembers(String bodyAcademyKey);
    
}
