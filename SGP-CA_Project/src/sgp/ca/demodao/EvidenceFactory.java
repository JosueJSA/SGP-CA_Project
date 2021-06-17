/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sgp.ca.domain.Article;
import sgp.ca.domain.Book;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Prototype;
import sgp.ca.domain.ReceptionWork;

public class EvidenceFactory {
    
    private final static List<Evidence> listEvidences = Arrays.asList(
        new Article(), 
        new Book(), 
        new Prototype(), 
        new ReceptionWork()
    );
    
    public static Evidence getEvidence(String evidenceType){
        Evidence evidence = null;
        for(Evidence evidenceIterator : listEvidences){
            if(evidenceType.equalsIgnoreCase(evidenceIterator.toString())){
                evidence = evidenceIterator.getSpecificEvidenceInstance(evidenceType);
                break;
            }
        }
        return evidence;
    }
    
    public static Evidence getEvidence(String evidenceType, String evidenceTitle, 
    boolean impactAB, String registrationResponsible, String registrationDate, String urlFile){
        Evidence evidence = null;
        for(Evidence evidenceIterator : listEvidences){
            if(evidenceType.equalsIgnoreCase(evidenceIterator.toString())){
                evidence = evidenceIterator.getSpecificEvidenceInstance(
                    evidenceType, 
                    evidenceTitle, 
                    impactAB, 
                    registrationResponsible, 
                    registrationDate, 
                    urlFile
                );
                break;
            }
        }
        return evidence;
    }
    
}
