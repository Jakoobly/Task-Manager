package TaskManager.exception;

public class InvalidTitleException extends RuntimeException {
    public InvalidTitleException(String message) {
        super(message);
    }

    public InvalidTitleException(String messsage, Throwable cause){
        super(messsage, cause);
    }
}
