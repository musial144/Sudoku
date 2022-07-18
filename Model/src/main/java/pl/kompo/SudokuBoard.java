package pl.kompo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class SudokuBoard implements Serializable, Cloneable {
    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;
    private List<SudokuField> element = Arrays.asList(new SudokuField[9]);

    @Id
    @GeneratedValue
    private Long id;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
                board[i][j].setFieldValue(0);
            }
        }
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public void solveGame() {

        while (!checkBoard()) {
            sudokuSolver.solve(this);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                }
            }
        }
    }

    private boolean checkBoard() {
        int k = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!getBox(i, j).verify() || !getColumn(k).verify() || !getRow(k).verify()) {
                    return false;
                }
                k++;
            }
        }
        return true;
    }

    public boolean checkBoardTest() {
        return checkBoard();
    }

    public SudokuRow getRow(int y) {
        SudokuRow row = new SudokuRow();
        for (int j = 0; j < 9; j++) {
            element.set(j, board[y][j]);
        }
        row.setElement(element);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        for (int i = 0; i < 9; i++) {
            element.set(i, board[i][x]);
        }
        column.setElement(element);
        return column;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int checkingColumn;
        int checkingRow = x - x % 3;
        checkingColumn = y - y % 3;
        int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                element.set(k, board[checkingRow + i][checkingColumn + j]);
                k++;
            }
        }
        box.setElement(element);
        return box;
    }

    @Override
    public String toString() {
        ToStringBuilder napis = new ToStringBuilder(this);
        for (int i = 0; i < 9; i++) {
            for (int j = 0;j < 9; j++) {
                napis.append(get(i,j));
            }
        }
        return napis.toString();
    }

    @Override
    public boolean equals(Object obj) {
        EqualsBuilder equal = new EqualsBuilder();
        equal.append(board, ((SudokuBoard) obj).board);
        return equal.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(17,37);
        hash.append(board);
        return hash.toHashCode();
    }

    @Override
    public SudokuField[][] clone() throws CloneNotSupportedException {
        return board.clone();
    }
}
