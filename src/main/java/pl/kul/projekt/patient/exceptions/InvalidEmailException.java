package pl.kul.projekt.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email is invalid")
public class InvalidEmailException extends Exception {

    public InvalidEmailException(String message) {
        super(message);
    }

}
