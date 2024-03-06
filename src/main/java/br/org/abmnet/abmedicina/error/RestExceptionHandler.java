package br.org.abmnet.abmedicina.error;

import br.org.abmnet.abmedicina.error.exception.AuthenticationException;
import br.org.abmnet.abmedicina.error.exception.ForbiddenTarefaException;
import br.org.abmnet.abmedicina.error.exception.TarefaNotFoundException;
import br.org.abmnet.abmedicina.error.exception.SerializationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TarefaNotFoundException.class)
    public ResponseEntity<?> handleMainNotFoundException(TarefaNotFoundException tarefaNotFoundException) {
        ErrorDetails errorDetails = ErrorDetails.builder().message(tarefaNotFoundException.getMessage()).build();

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenTarefaException.class)
    public ResponseEntity<?> handleForbiddenTarefaException(ForbiddenTarefaException forbiddenTarefaException) {
        ErrorDetails errorDetails = ErrorDetails.builder().message(forbiddenTarefaException.getMessage()).build();

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    //@Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    //@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(errors.toString())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .message("Bad request, body inválido, por favor verifique os dados.")
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SerializationException.class)
    public ResponseEntity<?> handleSerializationException(SerializationException serializationException) {
        ErrorDetails errorDetails = ErrorDetails.builder().message(serializationException.getMessage()).build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException authenticationException) {
        ErrorDetails errorDetails = ErrorDetails.builder().message(authenticationException.getMessage()).build();

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


}
