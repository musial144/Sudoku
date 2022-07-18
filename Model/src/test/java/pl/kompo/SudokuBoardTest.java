package pl.kompo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    SudokuBoard sudoku = new SudokuBoard(sudokuSolver);
    SudokuBoard sudoku1 = new SudokuBoard(sudokuSolver);


    @Test
    void solveGame(){
        int counter=0;
        sudoku.solveGame();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                counter += sudoku.get(i,j);
            }
        }
        assertEquals(405,counter);
    }

    @Test
    void getRowTest(){
        for(int i=0;i<9;i++) {
            sudoku.set(1,i,i+1);
        }
        assertTrue(sudoku.getRow(1).verify());
        for(int i=0;i<9;i++) {
            sudoku.set(1,i,1);
        }
        assertFalse(sudoku.getRow(1).verify());
    }

    @Test
    void getColumnTest(){
        for(int i=0;i<9;i++) {
            sudoku.set(i,1,i+1);
        }
        assertTrue(sudoku.getColumn(1).verify());
        for(int i=0;i<9;i++) {
            sudoku.set(i,1,1);
        }
        assertFalse(sudoku.getRow(1).verify());
    }

    @Test
    void getBoxTest(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sudoku.set(i,j,1);
            }
        }
        assertFalse(sudoku.getBox(1,1).verify());
        int k=1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sudoku.set(i,j,k);
                k++;
            }
        }
        assertTrue(sudoku.getBox(1,1).verify());
    }

    @Test
    void checkBoardTestBoxTest(){
        sudoku.solveGame();
        assertTrue(sudoku.checkBoardTest());

        sudoku.set(5,5,sudoku.get(5,5)+1);
        assertFalse(sudoku.checkBoardTest());
    }

    @Test
    void checkBoardTestColumnTest(){
        sudoku.solveGame();
        assertTrue(sudoku.checkBoardTest());

        int k=1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                sudoku.set(j,i,k);
                k++;
            }
        }
        assertFalse(sudoku.checkBoardTest());
    }

    @Test
    void toStringTest(){
        sudoku.solveGame();
        System.out.print(sudoku.toString());
    }

    @Test
    void equalsTest(){
        sudoku.solveGame();
        sudoku1.solveGame();
        while(sudoku.equals(sudoku1)){
            sudoku1.solveGame();
        }
        assertFalse(sudoku.equals(sudoku1));
    }

    @Test
    void hashCodeTest(){
        sudoku.solveGame();
        sudoku1.solveGame();

        while(sudoku.hashCode() == sudoku1.hashCode()){
            sudoku1.solveGame();
        }

        assertEquals(sudoku.hashCode(),sudoku.hashCode());

        assertFalse(sudoku.hashCode() == sudoku1.hashCode());

    }

    @Test
    void equalsAndHashCodeTest(){
        sudoku.solveGame();
        sudoku1.solveGame();

        while(sudoku.equals(sudoku1)){
            sudoku1.solveGame();
        }
        assertFalse(sudoku.equals(sudoku1));
        assertFalse(sudoku.hashCode() == sudoku1.hashCode());

    }

    @Test
    void checkBoardTestRowTest(){
        sudoku.solveGame();
        assertTrue(sudoku.checkBoardTest());
        System.out.println();System.out.println();System.out.println();
        int k=1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                sudoku.set(j,i,k);
                k++;
            }
        }

        k=4;
        for(int i = 0; i < 3; i++){
            for(int j = 3; j < 6; j++){

                if(i != 2 ){
                    sudoku.set(j,i,k);
                }else{
                    sudoku.set(j,i,k - 9);
                }
                k++;
            }
        }

        k=7;
        for(int i = 0; i < 3; i++){
            for(int j = 6; j < 9; j++){

                if(i == 0 ){
                    sudoku.set(j,i,k);
                }else{
                    sudoku.set(j,i,k - 9);
                }
                k++;
            }
        }
        assertFalse(sudoku.checkBoardTest());
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        sudoku.solveGame();
        SudokuField[][] sudokuBoard =  sudoku.clone();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j <9; j++){
                assertEquals( sudoku.get(i,j), sudokuBoard[i][j].getFieldValue());
            }
        }
    }
}
