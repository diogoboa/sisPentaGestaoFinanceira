package penta.sisPenta.gestaoFinanceira.Exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.EntityNotFoundException;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.PageNotFoundException;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.UnprocessableEntity;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.FalhaAoSerializarMensagem;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(Instant.now());
        error.setError("Recurso não encontrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentTypeMismatchException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(Instant.now());
        error.setError("Exceção de parametro REST ("+e.getParameter().getMethod().getName()+")");
        error.setMessage("Erro no parametro path: ("+e.getValue()+ "), tipo esperado: ("+e.getRequiredType().getSimpleName()+")"+ " referente a: ("+e.getName()+")");
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<StandardError> pageNotFoundException(PageNotFoundException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(Instant.now());
        error.setError("Pagina não existe");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<StandardError> accessDeniedException(org.springframework.security.access.AccessDeniedException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setTimestamp(Instant.now());
        error.setError("Permissão de acesso negada");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        error.setTimestamp(Instant.now());
        error.setError("Method não habilitado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<StandardError> unprocessableEntityException(UnprocessableEntity e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.setError("Processamento não habilitado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(FalhaAoSerializarMensagem.class)
    public ResponseEntity<StandardError> falhaAoSerializar(FalhaAoSerializarMensagem e) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(Instant.now());
        error.setError("Processamento não habilitado");
        error.setMessage(e.getMessage());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(ListenerExecutionFailedException.class)
    public ResponseEntity<StandardError> erroAoReceberMensagemRabbitMQ(ListenerExecutionFailedException e, HttpServletRequest request){

        // Customizar a mensagem de erro
        String mensagemDeErro = "Erro no processamento da mensagem rabbitMQ";

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.setError(mensagemDeErro);
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }


    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> accessDeniedException(org.springframework.http.converter.HttpMessageNotReadableException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.setError("Erro no conteudo(body) da requisicao");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.setError("Erro no conteudo(body) da requisicao");
        error.setMessage(e.getMostSpecificCause().getMessage());
        error.setPath(request.getRequestURI());

        /*se for uma violacao de duplicidade*/
        String regex_duplicate_unique = "^.*(DUPLICATE KEY).*(KEY) \\((.*)\\)=\\((.*)\\).*$";
        String message_duplicate_unique = error.getMessage().toUpperCase().replaceAll("\\n", "");
        if(message_duplicate_unique.matches(regex_duplicate_unique))
        {
            final Pattern pattern = Pattern.compile(regex_duplicate_unique, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(message_duplicate_unique);
            matcher.find();
            String coluna_banco_de_dados = matcher.group(3);
            String texto_duplicado = matcher.group(4);
            error.setMessage("O texto \""+texto_duplicado+"\" já existe e nao pode ser duplicado, verifique o campo \""+coluna_banco_de_dados+"\"");
        }


        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }




    @ExceptionHandler(org.springframework.dao.CannotAcquireLockException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(org.springframework.dao.CannotAcquireLockException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimestamp(Instant.now());
        error.setError("Recurso em conflito");
        error.setMessage("O recurso solicitado foi modificado em outro local");
        error.setPath(request.getRequestURI());





        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(org.springframework.transaction.IllegalTransactionStateException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(org.springframework.transaction.IllegalTransactionStateException e, HttpServletRequest request){

        StandardError error = new StandardError();
        error.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
        error.setTimestamp(Instant.now());
        error.setError("Recurso necessita de transação");
        error.setMessage("O recurso solicitado precisa estar em uma transação");
        error.setPath(request.getRequestURI());





        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(error);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,  HttpServletRequest request) {



        List<FieldError> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());



        String reposta = "";
        for (FieldError erro_encontrado : errors)
        {
            reposta += ("erro no campo: ("+erro_encontrado.getField()+"), "+erro_encontrado.getDefaultMessage())+"\n";

        }


        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(Instant.now());
        error.setError("Erro de validação");
        error.setMessage(reposta);
        error.setPath(request.getRequestURI());



        ResponseEntity<StandardError> responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
        return responseEntity;
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> execptionAll(Exception e, HttpServletRequest request){


        StandardError error = new StandardError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(Instant.now());
        error.setError("Exceção Genérica ("+e.getClass()+")");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }








}
