/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author josue
 */
public class EvidenceDAOFactory {
    
    private static final List<EvidenceDAO> evidencesDao = Arrays.asList(
        new ArticleDAO(),
        new BookDAO(),
        new PrototypeDAO(),
        new ReceptionWorkDAO()
    );
    
    public static EvidenceDAO getSpecificEvidenceDaoInstance(String evidenceType){
        EvidenceDAO evidenceDao = null;
        for(EvidenceDAO genericObjectDao : evidencesDao){
            if(genericObjectDao.toString().equalsIgnoreCase(evidenceType)){
                evidenceDao = genericObjectDao.getEvidenceDaoInstance(evidenceType);
                break;
            }
        }
        return evidenceDao;
    }
    
}
