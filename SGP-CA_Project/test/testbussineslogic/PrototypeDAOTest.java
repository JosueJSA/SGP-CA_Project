///**
// * @author estef
// * Last modification date format: 19-04-2021
// */
//
//package testbussineslogic;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import org.junit.Assert;
//import org.junit.Test;
//import sgp.ca.businesslogic.PrototypeDAO;
//import sgp.ca.dataaccess.ConnectionDatabase;
//import sgp.ca.domain.Prototype;
//
//public class PrototypeDAOTest {
//    private final ConnectionDatabase conexion = new ConnectionDatabase();
//    int rowsNumber;
//    
//    @Test
//    public void addedPrototypeTest() throws SQLException{
//        System.out.println("Add new prototype to dataBase");
//        Prototype newPrototype;
//        newPrototype = new Prototype("Caracteristicas del prototipo", "https://eminus.uv.mx/eminus4/", "Crecimiento de lenguajes de programación", "Titulo", "Mexico", "2021-02-01", "Si");
//        PrototypeDAO addPrototype = new PrototypeDAO();
//        addPrototype.addPrototype(newPrototype);
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Prototype");
//        queryResult.last();
//        rowsNumber = queryResult.getRow();
//        int expectedNumberRows = 1;
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//    
//    @Test
//    public void confirmationDeletedPrototypeTest() throws SQLException{
//        PrototypeDAO prototypeDao = new PrototypeDAO();
//        prototypeDao.deletePrototypebyURL("https://eminus.uv.mx/eminus4/");
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Prototype");
//        queryResult.last();
//        rowsNumber = queryResult.getRow();
//        int expectedNumberRows = 0;
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//}
