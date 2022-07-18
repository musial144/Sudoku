package pl.kompo.exceptions;

public class NoFileJdbcDaoException extends JdbcSudokuBoardDaoException {
    public NoFileJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
