package pl.kul.projekt.patient;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kul.projekt.patient.email.Validator;
import pl.kul.projekt.patient.exceptions.InvalidEmailException;
import pl.kul.projekt.patient.exceptions.PatientNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PatientService patientService;

    @Test
    void shouldThrowInvalidEmailException() {
        UUID id = UUID.randomUUID();
        Patient patient = new Patient(id, "Name", "Surname", "patient");

        InvalidEmailException result = assertThrows(InvalidEmailException.class, () -> patientService.add(patient));

        assertEquals("Invalid email", result.getMessage());
    }

    @Test
    void shouldReturnPatient() {
        UUID id = UUID.randomUUID();
        Patient patient = new Patient(id, "Name", "Surname", "patient@gmail.com");

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        Optional<Patient> result = patientService.get(id);

        assertEquals(patient, result.get());
    }

    @Test
    void shouldThrowPatientNotFoundException() {
        UUID id = UUID.randomUUID();

        PatientNotFoundException result = assertThrows(PatientNotFoundException.class, () -> patientService.getPatientEmail(id));

        assertEquals("Patient not found", result.getMessage());
    }
}

