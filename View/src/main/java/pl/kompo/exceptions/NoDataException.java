package pl.kompo.exceptions;

public class NoDataException extends ControllerException {
    public NoDataException() {
        super("There is no data in this file");
    }

    public NoDataException(String message) {
        super(message);
    }
}
