package pl.kompo.exceptions;

public class ClassNotFoundJdbcDaoException extends JdbcSudokuBoardDaoException {

    public ClassNotFoundJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
