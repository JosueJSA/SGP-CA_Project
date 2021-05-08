/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.ReceptionWork;

public interface IReceptionWorkDAO {
    public void addReceptionWork(ReceptionWork newReceptionWork);
    public List<ReceptionWork> getReceptionWorkList();
    public boolean updateReceptionWork(ReceptionWork receptionWork, String oldUrlFile);
    public void deleteReceptionWork(String urlFileReceptionWork);
}