/**
 * @author estef
 * Last modification date format: 07-05-2021
 */

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;

public class ChapterBook {
    private String urlFile;
    private String chapterBookTitle;
    private String registrationDate;
    private String registrationResponsible;
    private String pageNumberRange;
    private String urlFileBook;
    private List<String> students;
    private List<Integrant> integrants;
    private List<Collaborator> collaborators;

    public ChapterBook(String urlFile, String chapterBookTitle, String registrationDate, 
    String registrationResponsible, String pageNumberRange, String urlFileBook) {
        this.urlFile = urlFile;
        this.chapterBookTitle = chapterBookTitle;
        this.registrationDate = registrationDate;
        this.registrationResponsible = registrationResponsible;
        this.pageNumberRange = pageNumberRange;
        this.urlFileBook = urlFileBook;
        this.students = new ArrayList<>();
        this.integrants = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public ChapterBook() {
        this.students = new ArrayList<>();
        this.integrants = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public String getUrlFile() {
        return urlFile;
    }

    public String getChapterBookTitle() {
        return chapterBookTitle;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getRegistrationResponsible() {
        return registrationResponsible;
    }

    public String getPageNumberRange() {
        return pageNumberRange;
    }

    public String getUrlFileBook() {
        return urlFileBook;
    }

    public List<String> getStudents() {
        return students;
    }

    public List<Integrant> getIntegrants() {
        return integrants;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public void setChapterBookTitle(String chapterBookTitle) {
        this.chapterBookTitle = chapterBookTitle;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setRegistrationResponsible(String registrationResponsible) {
        this.registrationResponsible = registrationResponsible;
    }

    public void setPageNumberRange(String pageNumberRange) {
        this.pageNumberRange = pageNumberRange;
    }

    public void setUrlFileBook(String urlFileBook) {
        this.urlFileBook = urlFileBook;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public void setIntegrants(List<Integrant> integrants) {
        this.integrants = integrants;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
}
