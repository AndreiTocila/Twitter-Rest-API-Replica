package ro.uaic.info.fiipractic.exception.custom;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
