package ro.uaic.info.fiipractic.exception.custom;

public class CanNotDeletePostException extends RuntimeException{

    public CanNotDeletePostException(String message) {
        super(message);
    }
}
