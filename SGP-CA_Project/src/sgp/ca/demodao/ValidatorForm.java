/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Josue Alarcon
 */
public class ValidatorForm{
    
    public static void checkAlaphabeticalFields(List<TextField> fields, int characterSizeLimit) throws InvalidFormException{
        boolean isCorrect = true;
        for(TextField field : fields){
            chechkAlphabeticalField(field, characterSizeLimit);
        }
    }
    
    public static void checkAlaphabeticalTextAreas(List<TextArea> textAreas, int characterSizeLimit) throws InvalidFormException{
        for(TextArea area : textAreas){
            chechkAlphabeticalArea(area, characterSizeLimit);
        }
    }
    
    public static void chechkAlphabeticalField(TextField field, int characterSizeLimit) throws InvalidFormException{
        boolean correctField = false;
        if(!field.getText().isEmpty() && field.getText().length() < characterSizeLimit){
            if(!isNumberData(field.getText())){
                correctField = true;
                field.setStyle("-fx-border-color: green;");
            }else{
                field.setStyle("-fx-border-color: red;");
            }
        }else{
            field.setStyle("-fx-border-color: red;");
        }
        if(!correctField){
            throw new InvalidFormException("El campo: " + field.getAccessibleHelp() + ", es inválido");
        }
    }
    
    public static void chechkAlphabeticalArea(TextArea txtArea, int characterSizeLimit) throws InvalidFormException{
        boolean correctField = false;
        if(!txtArea.getText().isEmpty() && txtArea.getText().length() < characterSizeLimit){
            if(!isNumberData(txtArea.getText())){
                correctField = true;
                txtArea.setStyle("-fx-border-color: green;");
            }else{
                txtArea.setStyle("-fx-border-color: red;");
            }
        }else{
            txtArea.setStyle("-fx-border-color: red;");
        }
        if(!correctField){
            throw new InvalidFormException("El campo: " + txtArea.getAccessibleHelp()+ ", es inválido");
        }
    }
    
    public static boolean isNumberData(String textForCheck){
        boolean isNumber = false;
        try{
            double number = Double.parseDouble(textForCheck);
            isNumber = true;
        }finally{
            return isNumber;
        }
    }
    
    public static boolean isIntegerNumberData(String textForCheck){
        boolean isIntegerNumber = false;
        try{
            int number = Integer.parseInt(textForCheck);
            isIntegerNumber = true;
        }finally{
            return isIntegerNumber;
        }
    }
    
    public static void checkNotEmptyDateField(DatePicker date) throws InvalidFormException{
        if(date.getValue() == null){
            date.setStyle("-fx-border-color: red;");
            throw new InvalidFormException("El campo: " + date.getAccessibleHelp() + ", está vacío");
        }else{
            date.setStyle("-fx-border-color: green;");
        }
    }
    
    public static void isComboBoxSelected(ComboBox<String> comboBox) throws InvalidFormException{
        if(comboBox.getValue() == null){
            comboBox.setStyle("-fx-border-color: red;");
            throw new InvalidFormException("Campos sin seleccionar");
        }else{
            comboBox.setStyle("-fx-border-color: green;");
        }
    }
    
    public static String convertJavaDateToSQlDate(DatePicker date){
        String newDate = date.getValue().toString().replace("/", "-");
        return newDate;
    }
    
}
