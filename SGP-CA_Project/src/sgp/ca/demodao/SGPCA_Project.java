/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.demodao;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;


public class SGPCA_Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args){
        launch(args);   
        
//        GeneralResume general = new GeneralResume(
//                "UV-CA-127", 
//                "Ingenieria y Tecnología de Software", 
//                "Económico-Administrativa Xalapa", 
//                "Facultad de Estadística e Informática", 
//                "En consolidación", 
//                "El Cuerpo Académico se encuentra consolidado y es "
//                + "líder en Ingeniería de Software y áreas relacionadas; "
//                + "todos los miembros trabajan colaborativamente en actividades "
//                + "de docencia, vinculación, generación y aplicación del "
//                + "conocimiento en las que participan activamente estudiantes "
//                + "de licenciatura y posgrado. El Cuerpo Académico contribuye a "
//                + "la resolución socialmente pertinente de problemas de software; "
//                + "obteniendo productos innovadores y de amplio reconocimiento "
//                + "a nivel nacional e internacional.", 
//                "Generar conocimiento y formar recursos humanos en Ingeniería de Software que contribuyan al desarrollo de software de calidad; a través de proyectos de investigación cuyos resultados se trasladen a  la docencia y la sociedad; y se difundan en foros especializados y de divulgación, fortaleciendo la vinculación academia-industria.", 
//                "Desarrollar métodos, técnicas y herramientas para el desarrollo de software con un enfoque sistemático, disciplinado y cuantificable y apegado a estándares de calidad.", 
//                "2002-04-12", 
//                "2019-01-11"
//        );
//        
//        GeneralResumeDAO dao = new GeneralResumeDAO();
//        dao.addGeneralResume(general);
        
        Integrant integrant = new Integrant(
                "SAGA8906245M7", 
                "Angel Juan Sánchez García", 
                "angesanchez@uv.mx", 
                "SAGA890624HVZNRN09", 
                "Mexicano", 
                "2012-08-12", 
                "Licenciatura en Ingeniería de Software", 
                41306, 
                "2281394721", 
                "PTC", 
                "Integrante", 
                "angelsg89@hotmail.com", 
                "2288146210", 
                "141912288421700"
        );
        
        IntegrantDAO inte = new IntegrantDAO();
//        inte.addIntegrant(integrant, "UV-CA-127");
        
        integrant.addSchooling(new Schooling(
            "Lic. en Ingeniería en Tecnologías Estratégicas de la Información",
            "Universidad Anáhuac de Xalapa",
            "Licenciatura",
            "Veracruz",
            "Computación",
            "Ingeniería",
            "2003-06-08",
            "08759567"
        ));
        
        inte.addIntegrantStudies(integrant);

    }
    
}
