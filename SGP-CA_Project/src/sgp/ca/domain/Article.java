/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.domain;

public class Article extends Evidence{
    private double isnn;
    private String magazineName;
    private int index;
    private String magazineEditorial;
    private Magazine magazine;

    public Article(double isnn, String urlFile, String projectName, 
    String evidenceTitle, String country, String publicationDate, 
    boolean impactAB, String registrationDate, String registrationResponsible, 
    String studyDegree, String evidenceType){
        super(
            urlFile, projectName, evidenceTitle, country, 
            publicationDate, impactAB, registrationDate, 
            registrationResponsible, studyDegree, evidenceType
        );
        this.isnn = isnn; 
        magazine = new Magazine();
    }

    public Article(String evidenceType, String evidenceTitle, 
    boolean impactAB, String registrationResponsible, String registrationDate, String urlFile) {
        super(evidenceType, evidenceTitle, impactAB, registrationResponsible, registrationDate, urlFile);
    }

    public Article(){
        magazine = new Magazine();
    }

    public double getIsnn() {
        return isnn;
    }

    public void setIsnn(double isnn) {
        this.isnn = isnn;
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

    public String getMagazineEditorial() {
        return magazineEditorial;
    }

    public void setMagazineEditorial(String magazineEditorial) {
        this.magazineEditorial = magazineEditorial;
    }

    public Magazine getMagazine() {
        return magazine;
    }  
    
    public void setMagazine(Magazine magazine){
        this.magazine = magazine;
    }
    
    @Override
    public String toString(){
        return "Artículo";
    }

    @Override
    public Evidence getSpecificEvidenceInstance(String evidenceType) {
        Evidence evidence = null;
        if(this.toString().equalsIgnoreCase(evidenceType)){
            evidence = new Article();
        }
        return evidence;
    }

    @Override
    public Evidence getSpecificEvidenceInstance(String evidenceType, String evidenceTitle, 
    boolean impactAB, String registrationResponsible, String registrationDate, String urlFile) {
        Evidence evidence = null;
        if(this.toString().equalsIgnoreCase(evidenceType)){
            evidence = new Article(
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
