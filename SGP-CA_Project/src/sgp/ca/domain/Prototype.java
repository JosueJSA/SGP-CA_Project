///**
// * @author estef
// * Last modification date format: 19-04-2021
// */
//
//package sgp.ca.domain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Prototype extends Evidence{
//    private String features;
//    private List<String> students;
//    private List<Integrant> integrants;
//    private List<Collaborator> collaborators;
//
//    public Prototype(String urlFile, Project projectName, String evidenceTitle, 
//    String country, String publicationDate, boolean impactAB, String recordDate, 
//    String studyDegree, String features) {
//        super(urlFile, projectName, evidenceTitle, country, publicationDate, impactAB, recordDate, studyDegree);
//        this.features = features;
//        this.students = new ArrayList();
//        this.integrants = new ArrayList();
//        this.collaborators = new ArrayList();
//    }
//
//    public Prototype() {
//        this.students = new ArrayList();
//        this.integrants = new ArrayList();
//        this.collaborators = new ArrayList();
//    }
//
//    public String getFeatures() {
//        return features;
//    }
//
//    @Override
//    public List<String> getStudents() {
//        return students;
//    }
//
//    @Override
//    public List<Integrant> getIntegrants() {
//        return integrants;
//    }
//
//    @Override
//    public List<Collaborator> getCollaborators() {
//        return collaborators;
//    }
//
//    public void setFeatures(String features) {
//        this.features = features;
//    }
//
//    @Override
//    public void setStudents(List<String> students) {
//        this.students = students;
//    }
//
//    @Override
//    public void setIntegrants(List<Integrant> integrants) {
//        this.integrants = integrants;
//    }
//
//    @Override
//    public void setCollaborators(List<Collaborator> collaborators) {
//        this.collaborators = collaborators;
//    }
//    
//     @Override
//    public void addStudent(String newstudent){
//        students.add(newstudent);
//    }
//    
//    @Override
//    public void removeStudent(String student){
//        students.remove(student);
//    }
//    
//    @Override
//    public void addIntegrant(Integrant newIntegrant){
//        integrants.add(newIntegrant);
//    }
//    
//    @Override
//    public void removeIntegrant(Integrant integrant){
//        integrants.remove(integrant);
//    }
//    
//    @Override
//    public void addCollaborator(Collaborator newCollaborator){
//        collaborators.add(newCollaborator);
//    }
//    
//    @Override
//    public void removeCollaborator(Collaborator collaborator){
//        collaborators.remove(collaborator);
//    }
//}
