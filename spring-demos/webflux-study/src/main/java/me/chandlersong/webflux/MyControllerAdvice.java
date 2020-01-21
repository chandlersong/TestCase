package me.chandlersong.webflux;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleNotFoundException(Exception ex) {
        log.info("receive error: {}", ex.getClass().toString());
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(WebFluxController.GlobalException.class)
    public ResponseEntity<Void> handleNotFoundException(WebFluxController.GlobalException ex) {
        log.info("receive error: {}", ex.getClass().toString());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}


