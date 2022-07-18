package pl.kompo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SudokuFieldTest {
    private SudokuField field = new SudokuField();
    private SudokuField field1 = new SudokuField();

    @Test
    void toStringTest(){


        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            System.out.print(field.toString());
            System.out.println();
        }
    }

    @Test
    void compareToTest() {
        field.setFieldValue(1);
        field1.setFieldValue(2);

        assertEquals(0, field.compareTo(field));
        assertEquals(-1, field.compareTo(field1));
        assertEquals(1, field1.compareTo(field));
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        field.setFieldValue(1);

        SudokuField sudokuField = (SudokuField) field.clone();
        System.out.println(sudokuField);
        assertEquals(1, sudokuField.getFieldValue());
    }

    @Test
    void equalsTest() {
        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i+1);
            assertFalse(field.equals(field1));
        }

        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i);
            assertTrue(field.equals(field1));
        }
    }

    @Test
    void hashCodeTest(){
        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i+1);
            assertEquals(field.hashCode(),field.hashCode());
            assertEquals(field1.hashCode(),field1.hashCode());
            assertFalse(field.hashCode() == field1.hashCode());
        }

        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i);
            assertEquals(field.hashCode(),field1.hashCode());
            assertTrue(field.hashCode() == field1.hashCode());
        }


    }

    @Test
    void equalsAndHashCodeTest(){

        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i+1);

            assertFalse(field.equals(field1));
            assertFalse(field.hashCode() == field1.hashCode());
        }
        for(int i = 0; i < 9; i++){
            field.setFieldValue(i);
            field1.setFieldValue(i);

            assertTrue(field.equals(field1));
            assertTrue(field.hashCode() == field1.hashCode());
        }
    }
}
