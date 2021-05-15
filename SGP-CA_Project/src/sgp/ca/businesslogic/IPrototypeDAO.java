/**
 * @author Josué
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Prototype;

public interface IPrototypeDAO {
    
    public List<Prototype> getPrototypesByTitle(String title);
    public List<Prototype> getPrototypesBylimitDate(String limitDate);
    public List<Prototype> getPrototypeByURL(String studentName);
    public List<Prototype> getAllPrototypes();
    public List<Prototype> getPrototypesByIntegrantMailUv();
    public void addPrototype(Prototype newPrototype);
    public void updatePrototype(Prototype prototype, String oldUrlFile);
    public void deletePrototypebyURL(String urlFile);
    
}
