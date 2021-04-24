/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
import sgp.ca.domain.Integrant;

public interface IIntegrantDAO{
    
    public Integrant getIntegrantByUVmail(String emailUV);
    public void addIntegrant(Integrant newIntegrant);
    public void updateIntegrant(Integrant integrant, String oldMailUv);
    public void addIntegrantStudies(Connection connection, Integrant integrant);
    public void deleteIntegrantStudies(Connection connection, String rfc);
    public void unsubscribeIntegrantByEmailUV(String emailUV);
    public void subscribeIntegrantByEmailUV(String emailUV);
    
}
