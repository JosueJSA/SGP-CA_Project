/**
 * @author Josué
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Prototype;

public interface IPrototypeDAO {
    
    public List<Prototype> getPrototypesByIntegrantRFC(String rfc, String limitDate);
    public List<Prototype> getPrototypesByTitle(String title, String limitDate);
    public List<Prototype> getPrototypesByStudent(String studentName, String limitDate);
    public void addPrototype(Prototype newPrototype);
    public void updatePrototype(Prototype prototype, String oldUrlFile);
    public void deletePrototypebyURL(String urlFile);
    
}
