package pl.kul.projekt.appointment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class Appointment {

    private final UUID id;
    private final UUID doctorId;
    private final UUID patientId;
    private final LocalDate date;
    private final LocalTime time;

}
