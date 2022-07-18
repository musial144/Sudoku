package pl.kompo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    private boolean checkBox(SudokuBoard sudoku, int checkingNumber, int row, int column) {
        // SudokuBoard board = new SudokuBoard();
        int checkingColumn;
        int checkingRow = row - row % 3;
        for (int i = 0; i < 3; i++) {
            checkingColumn = column - column % 3;
            for (int j = 0; j < 3; j++) {
                if (checkingNumber == sudoku.get(checkingRow + i, checkingColumn + j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumn(SudokuBoard sudoku, int checkingNumber, int columnNumber) {
        //SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            if (checkingNumber == sudoku.get(i,columnNumber)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRow(SudokuBoard sudoku, int checkingNumber, int rowNumber) {
        //  SudokuBoard board = new SudokuBoard();
        for (int j = 0; j < 9; j++) {
            if (checkingNumber == sudoku.get(rowNumber,j)) {
                return true;
            }
        }
        return false;
    }

    public void solve(SudokuBoard sudoku) {
        int counter;
        Random r = new Random();
        List<Integer> toShuffle = Arrays.asList(new Integer[9]);

        for (int i = 0; i < 9; i++) {
            toShuffle.set(i, i + 1);
        }

        int shuffle = 0;
        boolean check;
        int elementOfList;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                check = true;
                //counter = 0;
                elementOfList = 0;
                Collections.shuffle(toShuffle);
                while (check) {
                    //shuffle = r.nextInt(9) + 1;
                    //counter++;
                    if (elementOfList > 8) {
                        for (int k = 0; k < 9; k++) {
                            for (int l = 0; l < 9; l++) {
                                sudoku.set(k,l,0);
                            }
                        }
                        i = 0;
                        j = 0;
                        elementOfList = 0;
                    }

                    if (checkBox(sudoku, toShuffle.get(elementOfList), i, j)) {
                        check = true;
                    } else if (checkRow(sudoku, toShuffle.get(elementOfList), i)) {
                        check = true;
                    } else if (checkColumn(sudoku, toShuffle.get(elementOfList), j)) {
                        check = true;
                    } else {
                        check = false;
                    }

                    elementOfList++;
                }
                sudoku.set(i,j,toShuffle.get(elementOfList - 1));
            }
        }
    }
}
