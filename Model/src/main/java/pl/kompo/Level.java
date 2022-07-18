package pl.kompo;

import java.util.Random;

public enum Level {
    easy(10), normal(20), hard(40);

    private int fieldNumber;

    public int getFieldNumber() {
        return fieldNumber;
    }

    Level(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }


    public SudokuField[][] deleteFields(SudokuBoard sudokuBoard) throws CloneNotSupportedException {
        SudokuField[][] sudokuFields = sudokuBoard.clone();
        Random r = new Random();
        int x;
        int y;


        for (int i = 0; i < fieldNumber; i++) {
            x = r.nextInt(9);
            y = r.nextInt(9);
            while (sudokuBoard.get(x,y) == 0) {
                x = r.nextInt(9);
                y = r.nextInt(9);
            }
            sudokuFields[x][y].setFieldValue(0);
        }

        return sudokuFields;
    }
}
