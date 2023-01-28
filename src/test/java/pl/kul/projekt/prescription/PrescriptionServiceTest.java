package pl.kul.projekt.prescription;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrescriptionServiceTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionService prescriptionService;

    @Test
    void shouldSavePrescription() {
        UUID id = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        UUID appointmentId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();

        Prescription prescription = new Prescription(id, doctorId, appointmentId, patientId, "Lek 1", LocalDate.now());

        when(prescriptionRepository.save(any())).thenReturn(prescription);
        Prescription result = prescriptionService.add(prescription);

        assertEquals(prescription, result);
    }

    @Test
    void shouldReturnPrescriptionByPatientId() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID doctorId1 = UUID.randomUUID();
        UUID doctorId2 = UUID.randomUUID();
        UUID appointmentId1 = UUID.randomUUID();
        UUID appointmentId2 = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();

        Prescription prescription = new Prescription(id1, doctorId1, appointmentId1, patientId, "Lek 1", LocalDate.now());
        Prescription prescription1 = new Prescription(id2, doctorId2, appointmentId2, patientId, "Lek 2", LocalDate.now());

        when(prescriptionRepository.getAllPrescriptionByPatientId(patientId))
                .thenReturn(List.of(prescription, prescription1));
        List<Prescription> result = prescriptionService.getAllPrescriptionByPatientId(patientId);

        assertEquals(List.of(prescription, prescription1), result);
    }

}
