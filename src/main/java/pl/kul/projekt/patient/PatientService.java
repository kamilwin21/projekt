package pl.kul.projekt.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kul.projekt.patient.exceptions.PatientNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public Patient add(Patient patient) {
        return patientRepository.save(patient);
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
            return patientRepository.findById(patientId).get().getEmail();
        } else {
            throw new PatientNotFoundException("Patient not found");
        }
    }
}
