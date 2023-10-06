package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.info("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    //위에 3개의 경우를 살펴보면 IllegalArgumentException같은 경우 그과 관련된 자식 오류까지 잡게되고
    //UserException 또한 그 자식 예외까지 처리를 하는데, 이 두가지가 아닌 다른 모든 예외는 마지막에
    //Exception 이 잡는식으로 설계 되어있다. Exception은 예외계의 object 같은 느낌이라 IllegalArguemnt, UserException 모두
    //포함될 수 있는데, 이 둘은 구체적으로 설계를 했기때문에 위의 로직이 잡지만 그 나머지 모든 예외는 마지막 로직이 잡는다고 생각하면 된다.

}
