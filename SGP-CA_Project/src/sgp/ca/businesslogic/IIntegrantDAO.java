/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.Integrant;

public interface IIntegrantDAO {
    
    public Integrant getIntegrant(String emailUV);
    public void addIntegrant(Integrant newIntegrant, String bodyAcademyKey);
    
}
