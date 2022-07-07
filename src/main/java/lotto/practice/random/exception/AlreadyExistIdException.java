package lotto.practice.random.exception;

public class AlreadyExistIdException extends RuntimeException{

    public AlreadyExistIdException() {
        super();
    }

    public AlreadyExistIdException(String message) {
        super(message);
    }

    public AlreadyExistIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistIdException(Throwable cause) {
        super(cause);
    }

    protected AlreadyExistIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
