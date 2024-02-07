package pbol.kelompok.mania.exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {}

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
