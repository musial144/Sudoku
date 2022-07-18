package pl.kompo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevelTest {

    @Test
    public void deleteFieldsTest() throws CloneNotSupportedException {
        Level level = Level.hard;
        assertEquals(40, level.getFieldNumber());

        for (int k = 0; k < 10; k++) {
            SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
            sudokuBoard.solveGame();
            SudokuField[][] sudokuField = level.deleteFields(sudokuBoard);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    assertEquals(sudokuBoard.get(i, j), sudokuField[i][j].getFieldValue());
                }
            }
        }
    }
}
