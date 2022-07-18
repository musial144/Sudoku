package pl.kompo;

public class SudokuBoardDaoFactory {

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
    public Dao<SudokuBoard> getJdbcDao(String fileName) {
        return new JdbcSudokuBoardDao(fileName);
    };
}
