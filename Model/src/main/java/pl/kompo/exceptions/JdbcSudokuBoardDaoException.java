package pl.kompo.exceptions;

public class JdbcSudokuBoardDaoException extends Exception{
    public JdbcSudokuBoardDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
