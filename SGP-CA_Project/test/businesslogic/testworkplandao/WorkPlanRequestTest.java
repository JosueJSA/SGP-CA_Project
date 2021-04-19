/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testworkplandao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.WorkPlanDAO;
import sgp.ca.domain.WorkPlan;


public class WorkPlanRequestTest{
    
    public final WorkPlanInitializer INITIALIZER = new WorkPlanInitializer();
    public final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();
    
    @Test
    public void testGetExistWorkPlanByEndDate(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        String generalTarjetExpected = "Mantener el grado en consolidación del cuerpo académico";
        INITIALIZER.cleanWorkPlanTestCaseData();
        Assert.assertEquals(generalTarjetExpected, workPlanRetrieved.getGeneralTarget());
    }
    
    @Test
    public void testGetNotExistWorkPlanByEndDate(){
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("20214-12-12");
        Assert.assertNull(workPlanRetrieved.getGeneralTarget());
    }
    
}
