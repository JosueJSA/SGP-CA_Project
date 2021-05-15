/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic.testarticledao;

import businesslogic.testevidences.EvidenceInitializer;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ArticleDAO;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MagazineDAO;
import sgp.ca.domain.Article;
import sgp.ca.domain.Magazine;

public class ArticleInsertionTest {
    
    private final MagazineDAO MAGAZINE_DAO = new MagazineDAO();
    private final ArticleDAO ARTICLE_DAO = new ArticleDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final EvidenceInitializer INITIALIZER = new EvidenceInitializer();
    
    @Test
    public void testCorrectArticleInsertion(){
        INITIALIZER.prepareEvidencesForTest();
        Article article = new Article(
            1324, "articuloParaTest.pdf", "ProyectoPrueba", 
            "articuloTest", "Mexico", "2020-11-12", true, "2020-12-24",
            "Angel Juan", "Licenciatura", "UV-CA-127"
        );
        Magazine magazine = new Magazine("RevistaParaTest",1, "Casillas", "Canada");
        if(MAGAZINE_DAO.getMagazineByName("RevistaParaTest").getEditorialCountry() == null){
            MAGAZINE_DAO.addMagazine(magazine);
        }
        article.getIntegrants().add(INTEGRANT_DAO.getIntegrantByUVmail("integrantTest@uv.mx"));
        article.getCollaborators().add(COLLABORATOR_DAO.getCollaboratorByUVmail("prueba@uv.mx"));
        article.getStudents().add("Johann");
        article.setMagazine(MAGAZINE_DAO.getMagazineByName("RevistaParaTest"));
        ARTICLE_DAO.addNewEvidence(article);
        Article retrievedArticle = (Article) ARTICLE_DAO.getEvidenceByUrl("articuloParaTest.pdf");
        ARTICLE_DAO.deleteEvidenceByUrl("articuloParaTest.pdf");
        INITIALIZER.cleanEvidencesForTest();
        Assert.assertEquals("articuloTest", retrievedArticle.getEvidenceTitle());
    }
    
}
