/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testevidences;

import businesslogic.testcollaboratordao.CollaboratorInitializer;
import businesslogic.testintegrantdao.IntegrantInitializer;
import sgp.ca.businesslogic.ArticleDAO;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MagazineDAO;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.domain.Article;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Magazine;
import sgp.ca.domain.Prototype;

/**
 *
 * @author Josue Alarcon
 */
public class EvidenceInitializer {
    
    private final IntegrantInitializer INTEGRANT_INITIALIZER = new IntegrantInitializer();
    private final CollaboratorInitializer COLLABORATOR_INITIALIZER = new CollaboratorInitializer();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final PrototypeDAO PROTOTYPE_DAO = new PrototypeDAO();
    private final MagazineDAO MAGAZINE_DAO = new MagazineDAO();
    private final ArticleDAO ARTICLE_DAO = new ArticleDAO();
    
    private void prepareParticipantsForTest(){
        INTEGRANT_INITIALIZER.prepareRequestTestCase();
        COLLABORATOR_INITIALIZER.prepareRequestTestCase();
    }
    
    public void prepareEvidencesForTest(){
        this.prepareParticipantsForTest();
        this.prepareInsertionPrototype();
        this.prepareInsertionArticle();
    }
    
    private void prepareInsertionPrototype(){
        if(PROTOTYPE_DAO.getEvidenceByUrl("prototipoPrueba.pdf").getEvidenceTitle() == null){
            Prototype prototypo = new Prototype(
                "prototipoPrueba.pdf", "ProyectoPrueba", 
                "prorotipo2", "Mexico", "2020-04-12", true, "2010-01-10",
                "Jorge Octavio Ocharan", "Licenciatura", "Prototipo", "Ninguna Característica"
            );
            prototypo.getIntegrants().add((Integrant) INTEGRANT_DAO.getMemberByUVmail("integrantTest@uv.mx"));
            prototypo.getCollaborators().add((Collaborator) COLLABORATOR_DAO.getMemberByUVmail("prueba@uv.mx"));
            prototypo.getStudents().add("Josue Alarcon");
            prototypo.getStudents().add("Johann Alexis");
            prototypo.getStudents().add("Bere Martínez");
            PROTOTYPE_DAO.addNewEvidence(prototypo);
        }   
    }
    
    private void prepareInsertionArticle(){
        if(ARTICLE_DAO.getEvidenceByUrl("articuloParaTest.pdf").getEvidenceTitle() == null){
            Article article = new Article(
                1324, "articuloParaTest.pdf", "ProyectoPrueba", 
                "articuloTest", "Mexico", "2020-11-12", true, "2020-12-24",
                "Angel Juan", "Licenciatura", "Articulo"
            );
            Magazine magazine = new Magazine("RevistaParaTest",1, "Casillas", "Canada");
            if(MAGAZINE_DAO.getMagazineByName("RevistaParaTest").getEditorialCountry() == null){
                MAGAZINE_DAO.addMagazine(magazine);
            }
            article.getIntegrants().add((Integrant) INTEGRANT_DAO.getMemberByUVmail("integrantTest@uv.mx"));
            article.getCollaborators().add((Collaborator) COLLABORATOR_DAO.getMemberByUVmail("prueba@uv.mx"));
            article.getStudents().add("Johann");
            article.setMagazine(MAGAZINE_DAO.getMagazineByName("RevistaParaTest"));
            ARTICLE_DAO.addNewEvidence(article);
        }
    }
    
    public void cleanEvidencesForTest(){
        PROTOTYPE_DAO.deleteEvidenceByUrl("prototipoPrueba.pdf");
        ARTICLE_DAO.deleteEvidenceByUrl("articuloParaTest.pdf");
        INTEGRANT_INITIALIZER.cleanIntegrantTest("INTEGRANTETEST");
        COLLABORATOR_INITIALIZER.cleanCollaboratorTest("COLABORADORTEST");
        MAGAZINE_DAO.deleteMagazineByName("RevistaParaTest");
    }

    
}
