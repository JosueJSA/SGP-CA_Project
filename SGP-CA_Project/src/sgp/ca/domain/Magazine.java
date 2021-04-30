/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.domain;


public class Magazine{
    
    private String magazineName;
    private int index;
    private String editorialName;
    private String editorialCountry;

    public Magazine(String magazineName, int index, String editorialName, String editorialCountry) {
        this.magazineName = magazineName;
        this.index = index;
        this.editorialName = editorialName;
        this.editorialCountry = editorialCountry;
    }
    
    public Magazine(){
        
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getEditorialName() {
        return editorialName;
    }

    public void setEditorialName(String editorialName) {
        this.editorialName = editorialName;
    }

    public String getEditorialCountry() {
        return editorialCountry;
    }

    public void setEditorialCountry(String editorialCountry) {
        this.editorialCountry = editorialCountry;
    }
    
    
}
