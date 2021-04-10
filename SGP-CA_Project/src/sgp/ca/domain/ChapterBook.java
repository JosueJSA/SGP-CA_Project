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
public class ChapterBook extends Evidence {
    private String bookName, pagesNumber;

    public ChapterBook(String bookName, String pagesNumber, String urlFile, String projectName, String evidenceTitle, String country, Date publicationDate, boolean impactAB) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.bookName = bookName;
        this.pagesNumber = pagesNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPagesNumber() {
        return pagesNumber;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPagesNumber(String pagesNumber) {
        this.pagesNumber = pagesNumber;
    }
    
    
}
