/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Member;



public interface IMemberDAO {
    
    public Member getMemberByUVmail(String emailUV);
    public boolean addMember(Member newMember);
    public boolean updateMember(Member member, String oldRFC);
    public boolean unsubscribeMemberByEmailUV(String emailUV);
    public boolean subscribeMemberByEmailUV(String emailUV);
    
}
