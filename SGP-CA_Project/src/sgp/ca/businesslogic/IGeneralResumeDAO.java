/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.GeneralResume;

public interface IGeneralResumeDAO {
    
    public boolean isBodyAcademyRegistered(String bodyAcademyKey);
    public List<String> getGeneralResumeKeys();
    public GeneralResume getGeneralResumeByKey(String bodyAcademyKey);
    public boolean addGeneralResume(GeneralResume newGeneralResume);
    public boolean updateGeneralResume(GeneralResume generalResume, String oldBodyAcademyKey);
    
}
