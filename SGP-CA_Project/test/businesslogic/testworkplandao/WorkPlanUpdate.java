/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testworkplandao;

import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.WorkPlanDAO;
import sgp.ca.domain.Action;
import sgp.ca.domain.Goal;
import sgp.ca.domain.WorkPlan;

public class WorkPlanUpdate {
    
    public final WorkPlanInitializer INITIALIZER = new WorkPlanInitializer();
    public final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();
    
    @Test
    public void testCorrectWorkPlanUpdateDifferentActions(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan oldWorkPlan = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        String firstGoalDescription = "Para el 2022 el 80 % de los integrantes del CA tiene el grado de doctor.";
        WorkPlan newWorkplan = new WorkPlan(2022 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2022-12-12", bodyAcademyKey);
        newWorkplan.getGoals().add(new Goal(newWorkplan.getWorkplanKey() + firstGoalDescription.hashCode(), "2020-12-12", "2021-01-11", false, firstGoalDescription));
        newWorkplan.getGoals().get(0).getActions().add(new Action(1, "2020-12-12", "1012-12-11", "2012-11-11", "Integrar a un nuevo PTC con grado de Doctor al CA", "KCV y XLR", "Documentción del nuevo PTC", false));
        newWorkplan.getGoals().get(0).getActions().add(new Action(1, "2020-12-12", "1012-12-11", "2012-11-11", "Documentción del nuevo PTC", "OOH", "Ninguno", true));
        WORKPLAN_DAO.updateWorkPlan(newWorkplan, oldWorkPlan);
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2022-12-12");
        WORKPLAN_DAO.deleteWorkPlan(newWorkplan);
        Assert.assertNotEquals(oldWorkPlan, workPlanRetrieved);
    }
    
    @Test
    public void testCorrectWorkPlanUpdateDifferentGoals(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan oldWorkPlan = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        String firstGoalDescription = "Para el 2022 el 80 % de los integrantes del CA tiene el grado de doctor.";
        String secondGoalDescription = "El 100% de los integrantes del CA cuentan con perfil deseable PRODEP.";
        String thirdGoalDescription = "Continuar con el desarrollo de los proyectos de investigación";
        WorkPlan workplan = new WorkPlan(2021 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2021-12-12", bodyAcademyKey);
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + firstGoalDescription.hashCode(), "2020-12-12", "2021-01-11", false, firstGoalDescription));
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + secondGoalDescription.hashCode(), "2020-12-12", "2021-05-10", false, secondGoalDescription));
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + thirdGoalDescription.hashCode(), "2020-12-12", "2021-05-10", false, thirdGoalDescription));
        workplan.getGoals().get(0).getActions().add(new Action(1, "2019-12-12", "1012-12-11", "2012-11-11", "Integrar a un nuevo PTC con grado de Doctor al CA", "KCV y XLR", "Documentción del nuevo PTC", false));
        workplan.getGoals().get(1).getActions().add(new Action(1, "2020-12-12", "1012-12-11", "2012-11-11", "Documentción del nuevo PTC", "OOH", "Ninguno", false));
        workplan.getGoals().get(2).getActions().add(new Action(1, "2021-12-12", "1012-12-11", "2012-11-11", "Mantener actualizado el Currículum en PRODEP.", "KVC", "Ninguno", false));
        WORKPLAN_DAO.updateWorkPlan(workplan, oldWorkPlan);
        
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        WORKPLAN_DAO.deleteWorkPlan(workplan);
        Assert.assertNotEquals(oldWorkPlan, workPlanRetrieved);
        
    }
    
    @Test
    public void testCorrectWorkPlanUpdateWithoutActionsAndGoals(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan oldWorkPlan = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        WorkPlan workplan = new WorkPlan(2021 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2021-12-12", bodyAcademyKey);
        WORKPLAN_DAO.updateWorkPlan(workplan, oldWorkPlan);
        
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        WORKPLAN_DAO.deleteWorkPlan(workplan);
        Assert.assertNotEquals(oldWorkPlan, workPlanRetrieved);
        
    }
    
    @Test
    public void testCorrectWorkPlanUpdateNotChanges(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan oldWorkPlan = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        
        WORKPLAN_DAO.updateWorkPlan(oldWorkPlan, oldWorkPlan);
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        WORKPLAN_DAO.deleteWorkPlan(oldWorkPlan);
        Assert.assertEquals(oldWorkPlan, workPlanRetrieved);
    }
    
    @Test
    public void testIncorrectWorkPlanUpdateInvalidActionInformation(){
        INITIALIZER.prepareRequestWorkPlanTestCase();
        WorkPlan oldWorkPlan = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        String firstGoalDescription = "Para el 2022 el 80 % de los integrantes del CA tiene el grado de doctor.";
        WorkPlan newWorkplan = new WorkPlan(2022 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2022-12-12", bodyAcademyKey);
        newWorkplan.getGoals().add(new Goal(newWorkplan.getWorkplanKey() + firstGoalDescription.hashCode(), "2020-12-12", "2021-01-11", false, firstGoalDescription));
        newWorkplan.getGoals().get(0).getActions().add(new Action(1, "2020-12-12", "1012-12-11", "2012-11-11", "Integrar a un nuevo PTC con grado de Doctor al CA", "KCV y XLR", "Documentción del nuevo PTC", false));
        newWorkplan.getGoals().get(0).getActions().add(new Action(1, "202020-12-12", "1012-12-11", "2012-11-11", "Documentción del nuevo PTC", "OOH", "Ninguno", true));
        WORKPLAN_DAO.updateWorkPlan(newWorkplan, oldWorkPlan);
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        INITIALIZER.cleanWorkPlanTestCaseData();
        Assert.assertEquals(oldWorkPlan, workPlanRetrieved);
    }
    
}
