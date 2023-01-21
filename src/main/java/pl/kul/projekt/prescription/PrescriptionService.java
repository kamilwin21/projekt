package pl.kul.projekt.prescription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public Prescription add(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void delete(Prescription prescription) {
        prescriptionRepository.delete(prescription);
    }

    public List<Prescription> getAllPrescriptionByPatientId(UUID id) {
        return prescriptionRepository.getAllPrescriptionByPatientId(id);
    }
}
