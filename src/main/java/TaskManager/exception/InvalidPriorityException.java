package TaskManager.exception;

public class InvalidPriorityException extends RuntimeException {
    public InvalidPriorityException(String message) {
        super(message);
    }
    public InvalidPriorityException(String message, Throwable cause){
        super(message, cause);
    }
}
