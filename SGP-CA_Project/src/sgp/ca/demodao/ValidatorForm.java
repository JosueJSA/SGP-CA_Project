/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Josue Alarcon
 */
public class ValidatorForm{
    
    public static boolean chechkAlphabeticalField(TextField field, int characterSizeLimit){
        boolean correctField = false;
        if(!field.getText().isEmpty() && field.getText().length() < characterSizeLimit){
            if(!isNumberData(field.getText())){
                correctField = true;
            }
        }
        return correctField;
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
    
    public static boolean isComboBoxSelected(ComboBox<String> comboBox){
        boolean isChecked = false;
        if(!(comboBox.getValue() == null)){
            isChecked = true;
        }
        return isChecked;
    }
    
}
