package pl.kul.projekt.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
