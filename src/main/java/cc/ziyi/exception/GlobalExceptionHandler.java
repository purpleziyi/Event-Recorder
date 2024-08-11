package cc.ziyi.exception;

import cc.ziyi.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // combines the functionality of @ControllerAdvice and @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // marks the method as a handler for exceptions
    public Result handleException(Exception e){
        e.printStackTrace();  // output the exception to console first

        // if the exception's message has content, it will be used as the error message,
        // otherwise "operation failed" will be used instead
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage(): "operation failed");
    }
}
