/**
 * @author estef
 * Last modification date format: 07-05-2021
 */

package sgp.ca.domain;

public class Book extends Evidence {
    private String publisher;
    private int editionsNumber;
    private double isbn;

    public Book(String urlFile, String projectName, boolean impactAB, String evidenceType, 
    String evidenceTitle, String registrationResponsible, String registrationDate, 
    String studyDegree, String publicationDate, String country, String publisher, 
    int editionsNumber, double isbn) {
        super(
            urlFile, projectName, evidenceTitle, country, publicationDate,
            impactAB,registrationDate, registrationResponsible, studyDegree, 
            evidenceType
        );
        this.publisher = publisher;
        this.editionsNumber = editionsNumber;
        this.isbn = isbn;
    }

    public Book() {
        
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

    @Override
    public String toString() {
        return super.toString() + "Edición: " + editionsNumber;
    }
}
