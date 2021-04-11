/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.Integrant;

public interface IIntegrantDAO{
    
    public Integrant getIntegrantByUVmail(String emailUV);
    public void addIntegrant(Integrant newIntegrant);
    public void updateIntegrant(Integrant integrant, String oldMailUv);
    
}
