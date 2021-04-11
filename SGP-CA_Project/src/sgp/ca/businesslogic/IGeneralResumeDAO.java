/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.GeneralResume;

public interface IGeneralResumeDAO {
    
    public GeneralResume getGeneralResumeByKey(String bodyAcademyKey);
    public void addGeneralResume(GeneralResume newGeneralResume);
    public void updateGeneralResume(GeneralResume generalResume, String oldBodyAcademyKey);
    
}
