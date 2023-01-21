package pl.kul.projekt.patient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Patient {

    private final UUID id;
    private final String name;
    private final String surname;
    private final String email;

}
