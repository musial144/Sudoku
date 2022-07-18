package pl.kompo.exceptions;

public class NoFileException extends ControllerException {
    public NoFileException() {
        super();
    }

    public NoFileException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoFileException(String msg) {
        super(msg);
    }
}
