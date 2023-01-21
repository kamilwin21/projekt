package pl.kul.projekt.doctor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Doctor {

    private final UUID id;
    private final String name;
    private final String surname;
    private final DoctorSpecialization specialization;

}
