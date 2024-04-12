package giuliochiarenza.progettoU5W2D5.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorsList;
    public BadRequestException(List<ObjectError> errorsList){
        super("errors in payload validation");
        this.errorsList = errorsList;
    }
}
