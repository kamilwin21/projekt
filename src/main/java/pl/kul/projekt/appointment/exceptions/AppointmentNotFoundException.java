package pl.kul.projekt.appointment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Appointment not found")
public class AppointmentNotFoundException extends Exception {

    public AppointmentNotFoundException(String message) {
        super(message);
    }

}
