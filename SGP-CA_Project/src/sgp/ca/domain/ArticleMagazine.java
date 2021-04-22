/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.domain;

public class ArticleMagazine {
    private String magazineName;
    private String index;
    private String magazinePublisher;

    public ArticleMagazine(String magazineName, String index, String magazinePublisher) {
        this.magazineName = magazineName;
        this.index = index;
        this.magazinePublisher = magazinePublisher;
    }
    
    public ArticleMagazine(){
        this.magazineName = "";
        this.index = "";
        this.magazinePublisher = "";
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
