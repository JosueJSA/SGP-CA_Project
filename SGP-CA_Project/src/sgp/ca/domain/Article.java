/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.domain;

public class Article extends Evidence{
    private double isnn;
    String magazineName;

    public Article(double isnn, String magazineName, String urlFile, String projectName, String evidenceTitle, String country, String publicationDate, String impactAB) {
        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB);
        this.isnn = isnn;
        this.magazineName = magazineName;
    }
    
    public Article(){
        this.isnn = 0;
        this.magazineName = "";
    }

    public double getIsnn() {
        return isnn;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setIsnn(double isnn) {
        this.isnn = isnn;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }
}
