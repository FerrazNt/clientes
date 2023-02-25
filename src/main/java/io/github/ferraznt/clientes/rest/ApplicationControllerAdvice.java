package io.github.ferraznt.clientes.rest;

import io.github.ferraznt.clientes.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    // Exception padronizada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map( objectError -> objectError.getDefaultMessage() )
                .collect( Collectors.toList() );
        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        String mensagemErro = ex.getReason(); // Usando getReason para melhorar as mensagens para o Client
        HttpStatus codigoStatus = ex.getStatus();
        ApiErrors apiErrors = new ApiErrors(mensagemErro);
        return new ResponseEntity(apiErrors, codigoStatus );
    }

    @ExceptionHandler(InvalidGrantException.class)
    public ResponseEntity handleResponseStatusException(InvalidGrantException ige){
        String mensagemErro = ige.getMessage(); // Usando getReason para melhorar as mensagens para o Client
        HttpStatus codigoStatus = HttpStatus.BAD_REQUEST;
        ApiErrors apiErrors = new ApiErrors("Login Inválido");
        return new ResponseEntity(apiErrors, codigoStatus );
    }
}
