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

    public Book(String publisher, int editionsNumber, double isbn, String urlFile, 
    String projectName, String evidenceTitle, String country, String publicationDate, 
    boolean impactAB, String registrationDate, String registrationResponsible, 
    String studyDegree, String bodyAcademyKey) {
        super(
            urlFile, projectName, evidenceTitle, country, 
            publicationDate, impactAB, registrationDate, 
            registrationResponsible, studyDegree, bodyAcademyKey
        );
        this.publisher = publisher;
        this.editionsNumber = editionsNumber;
        this.isbn = isbn;
        this.chapterBooks = new ArrayList<>();
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
    
}
