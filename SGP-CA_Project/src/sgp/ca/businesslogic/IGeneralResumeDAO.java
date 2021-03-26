/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Member;

public interface IGeneralResumeDAO {
    
    public GeneralResume getGeneralRsume(String bodyAcademyKey);
    public List<Member> searchMembersByName(String memberName);
    
}
