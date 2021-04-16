/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.domain;

import java.util.Date;

/**
 *
 * @author estef
 */
public class Article extends Evidence{
    private double isnn;
    ArticleMagazine magazineName;

    public Article(double isnn, ArticleMagazine magazineName, String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactAB) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.isnn = isnn;
        this.magazineName = magazineName;
    }

    public double getIsnn() {
        return isnn;
    }

    public ArticleMagazine getMagazineName() {
        return magazineName;
    }

    public void setIsnn(double isnn) {
        this.isnn = isnn;
    }

    public void setMagazineName(ArticleMagazine magazineName) {
        this.magazineName = magazineName;
    }
}
