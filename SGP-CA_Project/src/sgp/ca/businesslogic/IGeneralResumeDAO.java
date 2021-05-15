/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.GeneralResume;

public interface IGeneralResumeDAO {
    
    public List<String> getGeneralResumeKeys();
    public GeneralResume getGeneralResumeByKey(String bodyAcademyKey);
    public void addGeneralResume(GeneralResume newGeneralResume);
    public void updateGeneralResume(GeneralResume generalResume, String oldBodyAcademyKey);
    
}
