/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import sgp.ca.domain.Prototype;

public interface IPrototypeDAO {
    public Prototype getPrototypebyURL(String urlFile);
    public void addPrototype(Prototype newPrototype);
    public void updatePrototype(Prototype prototype, String oldUrlFile);
    public boolean deletePrototypebyURL(String urlFile);
}
