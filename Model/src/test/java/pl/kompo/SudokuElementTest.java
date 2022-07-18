package pl.kompo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuElementTest {
    private SudokuElement element = new SudokuElement();
    private SudokuElement element1 = new SudokuElement();
    private List<SudokuField> field = Arrays.asList(new SudokuField[9]);
    private List<SudokuField> field1 = Arrays.asList(new SudokuField[9]);

    @Test
    void verifyTest(){
        for(int i = 0; i < 9; i++){
            field.set(i,new SudokuField());
        }

        for(int i = 0; i < 9; i++){
            field.get(i).setFieldValue(5);
        }
        element.setElement(field);
        assertFalse(element.verify());

        for(int i = 0; i < 9; i++){
            field.get(i).setFieldValue(10);
        }
        element.setElement(field);
        assertFalse(element.verify());

        for(int i = 0; i < 9; i++){
            field.get(i).setFieldValue(0);
        }
        element.setElement(field);
        assertFalse(element.verify());

        for(int i = 0; i < 9; i++){
            field.get(i).setFieldValue(i+1);
        }
        element.setElement(field);
        assertTrue(element.verify());
    }

    @Test
    void toStringTest(){
        for(int i = 0; i < 9; i++){
            field.set(i,new SudokuField());
        }
                for(int i = 0; i < 9; i++) {
                    field.get(i).setFieldValue(i+1);
                }
                element.setElement(field);
                System.out.print(field.get(1).toString());
                System.out.print(element.toString());
        }

    @Test
    void equalsTest() {
        for(int i = 0; i < 9; i++){
            field.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++){
            field1.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++) {
            field.get(i).setFieldValue(i+1);
        }
        element.setElement(field);
        for(int i = 0; i < 9; i++) {
            field1.get(i).setFieldValue(2);
        }
        element1.setElement(field1);
        assertFalse(element.equals(element1));
        assertFalse(field.get(5).equals(field1.get(5)));
        assertTrue(field.get(1).equals(field1.get(1)));
    }

    @Test
    void hashCodeTest(){
        for(int i = 0; i < 9; i++){
            field.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++){
            field1.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++) {
            field.get(i).setFieldValue(i+1);
        }
        element.setElement(field);
        for(int i = 0; i < 9; i++) {
            field1.get(i).setFieldValue(2);
        }

        assertEquals(element.hashCode(),element.hashCode());
        assertEquals(element1.hashCode(),element1.hashCode());

        assertFalse(element.hashCode() == element1.hashCode());

    }

    @Test
    void equalsAndHashCodeTest(){

        for(int i = 0; i < 9; i++){
            field.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++){
            field1.set(i,new SudokuField());
        }
        for(int i = 0; i < 9; i++) {
            field.get(i).setFieldValue(i+1);
        }
        element.setElement(field);
        for(int i = 0; i < 9; i++) {
            field1.get(i).setFieldValue(2);
        }

        assertFalse(element.equals(element1));
        assertFalse(element.hashCode() == element1.hashCode());

    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        for(int i = 0; i < 9; i++) {
            field.set(i, new SudokuField());
        }
        element.setElement(field);
        SudokuElement cloneElement = (SudokuElement) element.clone();

        assertTrue(cloneElement.equals(element));
    }
}
