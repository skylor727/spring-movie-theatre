package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@org.springframework.web.bind.annotation.ControllerAdvice
@ResponseBody
public class ControllerAdvice {
    @ExceptionHandler(OutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(OutOfBoundsException exception) {
        return exception.getMessage();
    }
}
