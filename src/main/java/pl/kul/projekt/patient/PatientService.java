package pl.kul.projekt.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kul.projekt.patient.email.Validator;
import pl.kul.projekt.patient.exceptions.InvalidEmailException;
import pl.kul.projekt.patient.exceptions.PatientNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final Validator validator;

    public Patient add(Patient patient) throws InvalidEmailException {
        if (validator.isValid(patient.getEmail())) {
            return patientRepository.save(patient);
        } else {
            throw new InvalidEmailException("Invalid email");
        }
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    public Optional<Patient> get(UUID id) {
        return patientRepository.findById(id);
    }

    public String getPatientEmail(UUID patientId) throws PatientNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            return patient.get().getEmail();
        } else {
            throw new PatientNotFoundException("Patient not found");
        }
    }
}
