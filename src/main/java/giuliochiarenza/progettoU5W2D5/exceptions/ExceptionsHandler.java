package giuliochiarenza.progettoU5W2D5.exceptions;

import giuliochiarenza.progettoU5W2D5.payloads.ErrorsPayload;
import giuliochiarenza.progettoU5W2D5.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // ERROR 400
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        if (ex.getErrorsList() != null) {
            String message = ex.getErrorsList().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            return new ErrorsPayload(message, LocalDateTime.now());
        } else {
            return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
        }
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // ERROR 404
    public ErrorsPayload handleNotFound(NotFoundException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsResponseDTO handleForbidden(AccessDeniedException ex){
        return new ErrorsResponseDTO("Non hai accesso a questa funzionalità", LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // ERROR 500
    public ErrorsPayload handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayload("server side problem", LocalDateTime.now());
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }
}
