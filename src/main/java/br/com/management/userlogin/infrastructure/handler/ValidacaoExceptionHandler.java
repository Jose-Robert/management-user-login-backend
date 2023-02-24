package br.com.management.userlogin.infrastructure.handler;

import br.com.management.userlogin.infrastructure.exception.*;
import br.com.management.userlogin.infrastructure.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ValidacaoExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler({ CampoObrigatorioException.class })
    public ResponseEntity<Object> handleEsteCampoObrigatorioException(CampoObrigatorioException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "validacao.campo-obrigatorio", args);
    }

    @ExceptionHandler({ CampoTamanhoMaximoException.class })
    public ResponseEntity<Object> handleCampoTamanhoMaximoException(CampoTamanhoMaximoException exception, WebRequest request) {
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "validacao.campo-tamanho-maximo", exception.getArgs());
    }

    @ExceptionHandler({ EmailInvalidException.class })
    public ResponseEntity<Object> handleEmailInvalidException(EmailInvalidException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "validacao.email-invalido", args);
    }

    @ExceptionHandler({ EmailAlreadyInUseException.class })
    public ResponseEntity<Object> handleEmailAlreadyInUseException(EmailAlreadyInUseException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "validacao.email-existente", args);
    }

    @ExceptionHandler({ RecursoNaoEncontradoException.class })
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.NOT_FOUND, request, "validacao.recurso-nao-encontrado", args);
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.NOT_FOUND, request, "usuario.nao-encontrado", args);
    }

    @ExceptionHandler({ TokenHeaderErrorException.class })
    public ResponseEntity<Object> handleTokenHeaderEmptyException(TokenHeaderErrorException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.UNAUTHORIZED, request, "validacao.token-header", args);
    }

    @ExceptionHandler({ PasswordInvalidoException.class })
    public ResponseEntity<Object> handlePasswordInvalidoException(PasswordInvalidoException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.UNAUTHORIZED, request, "usuario.nao-encontrado", args);
    }

    @ExceptionHandler({ SessionInvalidException.class })
    public ResponseEntity<Object> handleSessionInvalidException(SessionInvalidException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.UNAUTHORIZED, request, "validacao.session-invalid", args);
    }

    @ExceptionHandler({ InvalidPasswordPatternException.class })
    public ResponseEntity<Object> handleInvalidPasswordPatternException(InvalidPasswordPatternException exception, WebRequest request) {
        Object[] args = { exception.getMessage() };
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "validacao.invalid-password-pattern", args);
    }

    protected ResponseEntity<Object> handlerException(Exception exception, HttpStatus status, WebRequest request, String key, Object[] args) {
        ApiError response = new ApiError(messageService.getMessage(key, args));
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }

}
