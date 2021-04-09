/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.GeneralResume;

public interface IGeneralResumeDAO {
    
    public GeneralResume getGeneralResumeByKey(String bodyAcademyKey);
    public void addGeneralResume(GeneralResume newGeneralResume);
    public void updateGeneralResume(GeneralResume generalResume, String oldBodyAcademyKey);
    
}
