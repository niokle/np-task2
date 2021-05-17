package application.exception;

public class RejectedNotFoundException extends Exception {
    public RejectedNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
