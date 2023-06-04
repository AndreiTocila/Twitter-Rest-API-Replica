package ro.uaic.info.fiipractic.exception.custom;

public class PostNotFoundException extends RuntimeException{

        public PostNotFoundException(String message) {
            super(message);
        }
}
