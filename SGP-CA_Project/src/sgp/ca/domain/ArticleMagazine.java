/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.domain;

/**
 *
 * @author estef
 */
public class ArticleMagazine {
    private String magazineName, index, magazinePublisher;

    public ArticleMagazine(String magazineName, String index, String magazinePublisher) {
        this.magazineName = magazineName;
        this.index = index;
        this.magazinePublisher = magazinePublisher;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public String getIndex() {
        return index;
    }

    public String getMagazinePublisher() {
        return magazinePublisher;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setMagazinePublisher(String magazinePublisher) {
        this.magazinePublisher = magazinePublisher;
    }
    
    
}
