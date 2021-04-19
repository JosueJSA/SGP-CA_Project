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


public class WorkPlanInsertTest {
    
    public final WorkPlanInitializer INITIALIZER = new WorkPlanInitializer();
    public final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();    
    private WorkPlan workplan;
    
    @Test
    public void testCorrectWorkPlanInsert(){
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        String firstGoalDescription = "Para el 2022 el 80 % de los integrantes del CA tiene el grado de doctor.";
        String secondGoalDescription = "El 100% de los integrantes del CA cuentan con perfil deseable PRODEP.";
        workplan = new WorkPlan(2021 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2021-12-12", bodyAcademyKey);
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + firstGoalDescription.hashCode(), "2012-12-12", "2021-01-11", false, firstGoalDescription));
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + secondGoalDescription.hashCode(), "2012-12-12", "2021-05-10", false, secondGoalDescription));
        workplan.getGoals().get(0).getActions().add(new Action(1, "2012-12-12", "1012-12-11", "2012-11-11", "Integrar a un nuevo PTC con grado de Doctor al CA", "KCV y XLR", "Documentción del nuevo PTC", false));
        workplan.getGoals().get(1).getActions().add(new Action(1, "2012-12-12", "1012-12-11", "2012-11-11", "Documentción del nuevo PTC", "OOH", "Ninguno", false));
        workplan.getGoals().get(1).getActions().add(new Action(2, "2012-12-12", "1012-12-11", "2012-11-11", "Mantener actualizado el Currículum en PRODEP.", "KVC", "Ninguno", false));
        WORKPLAN_DAO.addWorkPlan(workplan);
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        WORKPLAN_DAO.deleteWorkPlan(workplan);
        Assert.assertEquals(workplan, workPlanRetrieved);
    }
    
    @Test
    public void testIncorrectWorkPlanInsertInvalidData(){
        String generalTarget = "Mantener el grado en consolidación del cuerpo académico";
        String bodyAcademyKey = "UV-CA-127";
        String firstGoalDescription = "Para el 2022 el 80 % de los integrantes del CA tiene el grado de doctor.";
        String secondGoalDescription = "El 100% de los integrantes del CA cuentan con perfil deseable PRODEP.";
        workplan = new WorkPlan(2021 + bodyAcademyKey.hashCode(), 2, generalTarget, "2020-12-12", "2021-12-12", bodyAcademyKey);
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + firstGoalDescription.hashCode(), "2012-12-12", "2021-01-11", false, firstGoalDescription));
        workplan.getGoals().add(new Goal(workplan.getWorkplanKey() + secondGoalDescription.hashCode(), "2012-12-12", "2021-05-10", false, secondGoalDescription));
        workplan.getGoals().get(0).getActions().add(new Action(1, "2012-12-12", "1012-12-11", "2012-11-11", "Integrar a un nuevo PTC con grado de Doctor al CA", "KCV y XLR", "Documentción del nuevo PTC", false));
        workplan.getGoals().get(1).getActions().add(new Action(1, "2012-12-12", "1012-12-11", "2012-11-11", "Documentción del nuevo PTC", "OOH", "Ninguno", false));
        workplan.getGoals().get(1).getActions().add(new Action(2, "2012-12-12", "22012-12-11", "2012-11-11", "Mantener actualizado el Currículum en PRODEP.", "KVC", "Ninguno", false));
        WORKPLAN_DAO.addWorkPlan(workplan);
        WorkPlan workPlanRetrieved = WORKPLAN_DAO.getWorkPlan("2021-12-12");
        Assert.assertNull(workPlanRetrieved.getGeneralTarget());
    }
    
}
