package Model.Exceptions;

public class MediaAlreadyExistsException extends RuntimeException {
    public MediaAlreadyExistsException(String message) {
        super(message);
    }
}
