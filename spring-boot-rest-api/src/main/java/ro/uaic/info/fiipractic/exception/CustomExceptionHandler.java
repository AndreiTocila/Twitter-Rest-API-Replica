package ro.uaic.info.fiipractic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.uaic.info.fiipractic.exception.custom.*;


@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(UserNotFoundException exception) {

        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(PostNotFoundException exception) {

        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmptyFeedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String feed(EmptyFeedException exception) {

        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PostAlreadyYoursException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String repost (PostAlreadyYoursException exception)
    {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserDontFollowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String followUser(UserDontFollowException exception) {

        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CanNotDeletePostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String deletePost(CanNotDeletePostException exception) {

        return exception.getMessage();
    }

}
