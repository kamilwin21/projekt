package pl.kul.projekt.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Patient not found")
public class PatientNotFoundException extends Exception {

    public PatientNotFoundException(String message) {
        super(message);
    }

}
