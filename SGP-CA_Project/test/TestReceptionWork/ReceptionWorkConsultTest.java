
package TestReceptionWork;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.ReceptionWork;

public class ReceptionWorkConsultTest {
    @Test
    public void  getReceptionWorkListTest() throws SQLException{
        List<ReceptionWork> listReceptionWork = new ArrayList<>();
        System.out.println(" getReceptionWorkList");
        ReceptionWorkDAO instanceReceptionWorkList = new ReceptionWorkDAO(); 
        listReceptionWork = instanceReceptionWorkList.getReceptionWorkList();
        Assert.assertTrue(!listReceptionWork.isEmpty());
    }
}
