
package TestReceptionWork;


import domain.ReceptionWork;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businessLogic.ReceptionWorkDAO;

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
