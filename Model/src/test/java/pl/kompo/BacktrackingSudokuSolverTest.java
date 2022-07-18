package pl.kompo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {
    SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    SudokuBoard board = new SudokuBoard(sudokuSolver);


    @Test
    void solveTest(){

        sudokuSolver.solve(board);
        int counter=0;

        for (int i=0; i < 9; i++) {
            for (int j=0; j <9; j++) {
                counter += board.get(i,j);
            }
        }

        assertEquals(405,counter);

    }
}
