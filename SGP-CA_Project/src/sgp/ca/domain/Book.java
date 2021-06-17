/**
 * @author estef
 * Last modification date format: 07-05-2021
 */

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;

public class Book extends Evidence {
    private String publisher;
    private int editionsNumber;
    private double isbn;
    private List<ChapterBook> chapterBooks;

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
        this.chapterBooks = new ArrayList<>();
    }

    public Book(String evidenceType, String evidenceTitle, 
    boolean impactAB, String registrationResponsible, String registrationDate, String urlFile) {
        super(evidenceType, evidenceTitle, impactAB, registrationResponsible, registrationDate, urlFile);
    }

    public Book() {
        this.chapterBooks = new ArrayList<>();
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
    
    public ChapterBook getChapterBookByURLFile(String urlFile){
        ChapterBook chapterBookReturn = null;
        for(ChapterBook chapterBook : this.chapterBooks){
            if(chapterBook.getUrlFile() == urlFile){
                chapterBookReturn = chapterBook;
            }
        }
        return chapterBookReturn;
    }

    public List<ChapterBook> getChapterBooks() {
        return chapterBooks;
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

    public void setChapterBooks(List<ChapterBook> chapterBooks) {
        this.chapterBooks = chapterBooks;
    }
    
    @Override
    public String toString(){
        return "Libro";
    }

    @Override
    public Evidence getSpecificEvidenceInstance(String evidenceType) {
        Evidence evidence = null;
        if(this.toString().equalsIgnoreCase(evidenceType)){
            evidence = new Book();
        }
        return evidence;
    }

    @Override
    public Evidence getSpecificEvidenceInstance(String evidenceType, String evidenceTitle, 
    boolean impactAB, String registrationResponsible, String registrationDate, String urlFile) {
        Evidence evidence = null;
        if(this.toString().equalsIgnoreCase(evidenceType)){
            evidence = new Book(
                evidenceType,
                evidenceTitle,
                impactAB,
                registrationResponsible,
                registrationDate,
                urlFile
            );
        }
        return evidence;
    }
    
}
