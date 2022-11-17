package tech.devinhouse.devgram.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.devinhouse.devgram.dto.ErroResponse;
import tech.devinhouse.devgram.exception.RegistroExistenteException;
import tech.devinhouse.devgram.exception.RegistroNaoEncontradoException;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Object> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegistroExistenteException.class)
    public ResponseEntity<Object> handleRegistroExistenteException(RegistroExistenteException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> camposComErros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String nomeDoCampo = ((FieldError) error).getField();
            String msgDeErro = error.getDefaultMessage();
            camposComErros.put(nomeDoCampo, msgDeErro);
        });
        ErroResponse erro = new ErroResponse("Erro de validação", camposComErros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> camposComErros = new HashMap<>();
        ex.getConstraintViolations().forEach(e -> {
            Iterator<Path.Node> iterator = e.getPropertyPath().iterator();
            String nomeDoCampo = null;
            while(iterator.hasNext()) {
                nomeDoCampo = iterator.next().getName();
            }
            String msgDeErro = e.getMessage();
            camposComErros.put(nomeDoCampo, msgDeErro);
        });
        ErroResponse erro = new ErroResponse("Erro de validação", camposComErros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }



}
