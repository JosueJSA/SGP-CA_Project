/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package businesslogic;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.domain.GeneralResume;

public class GeneralResumeInsertTest {
    
    @Test
    public void testCorrectGeneralResumeInsertion(){
        GeneralResumeDAO generalResumeDAO = new GeneralResumeDAO();
        GeneralResume generalResume = new GeneralResume(
            "UV-CA-777", 
            "Física", 
            "Ingenierías", 
            "Facultad de Física", 
            "Consolidado", 
            "El Cuerpo Académico se encuentra consolidado y es líder en investigación cuántica.", 
            "Generar conocimiento práctico y teórico", 
            "Desarrollar métodos, técnicas y herramientas para la investigación aritmética.", 
            "2012-12-25", 
            "2019-01-11"
        );
        generalResumeDAO.addGeneralResume(generalResume);
        boolean isRegistered = false;
        if(generalResumeDAO.getGeneralResumeByKey("UV-CA-777").getBodyAcademyKey().equals(generalResume.getBodyAcademyKey())){
            isRegistered = true;
        }
        Assert.assertTrue(isRegistered);
    }
    
//    @Test(expected = MySQLIntegrityConstraintViolationException.class)
//    public void testDuplicatedGeneralResumeInsertion(){
//        GeneralResumeDAO generalResumeDAO = new GeneralResumeDAO();
//        GeneralResume generalResume = new GeneralResume(
//            "UV-CA-777", 
//            "Física", 
//            "Ingenierías", 
//            "Facultad de Física", 
//            "Consolidado", 
//            "El Cuerpo Académico se encuentra consolidado y es líder en investigación cuántica.", 
//            "Generar conocimiento práctico y teórico", 
//            "Desarrollar métodos, técnicas y herramientas para la investigación aritmética.", 
//            "2012-12-25", 
//            "2019-01-11"
//        );
//        generalResumeDAO.addGeneralResume(generalResume);
////        boolean isRegistered = false;
////        if(generalResumeDAO.getGeneralResumeByKey("UV-CA-777").getBodyAcademyKey().equals(generalResume.getBodyAcademyKey())){
////            isRegistered = true;
////        }
////        Assert.assertTrue(isRegistered);
//    }
    
    

}
    