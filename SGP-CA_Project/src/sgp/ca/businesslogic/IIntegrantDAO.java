/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;

public interface IIntegrantDAO {
    
    public Integrant getIntegrantByUVmail(String emailUV);
    public void addIntegrant(Integrant newIntegrant, String bodyAcademyKey);
    public List<Schooling> getIntegrantStudies(String integrantRfc);
    public void addIntegrantStudies(Integrant integrant);
    
}
