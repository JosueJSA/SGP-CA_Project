/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package sgp.ca.businessLogic;

import domain.ReceptionWork;
import java.util.List;

public interface IReceptionWorkDAO {
    public void addReceptionWork(ReceptionWork newReceptionWork);
    public List<ReceptionWork> getReceptionWorkList();
}