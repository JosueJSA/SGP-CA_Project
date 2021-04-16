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
public class Book extends Evidence {
    private String publisher;
    private int editionsNumber;
    private double isbn;

    public Book(String publisher, int editionsNumber, double isbn, String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactAB) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.publisher = publisher;
        this.editionsNumber = editionsNumber;
        this.isbn = isbn;
    }
    
    public Book(){
    }

    public String getPublisher() {
        return publisher;
    }

    public int getEditionsNumber() {
        return editionsNumber;
    }

    public double getIsbn() {
        return isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setEditionsNumber(int editionsNumber) {
        this.editionsNumber = editionsNumber;
    }

    public void setIsbn(double isbn) {
        this.isbn = isbn;
    }
}
