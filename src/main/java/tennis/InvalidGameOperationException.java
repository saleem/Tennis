package tennis;

public class InvalidGameOperationException extends RuntimeException {
    public InvalidGameOperationException(String message) {
        super(message);
    }
}
