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
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();
        Prescription prescription = new Prescription(id, id2, id3, id4, "Lek 1", LocalDate.now());

        when(prescriptionRepository.save(any())).thenReturn(prescription);
        Prescription result = prescriptionService.add(prescription);

        assertEquals(prescription, result);
    }

    @Test
    void shouldReturnPrescriptionByPatientId() {
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();
        UUID id5 = UUID.randomUUID();
        UUID id6 = UUID.randomUUID();
        UUID id7 = UUID.randomUUID();

        Prescription prescription = new Prescription(id, id2, id3, id4, "Lek 1", LocalDate.now());
        Prescription prescription1 = new Prescription(id5, id6, id7, id4, "Lek 1", LocalDate.now());

        when(prescriptionRepository.getAllPrescriptionByPatientId(id4))
                .thenReturn(List.of(prescription, prescription1));
        List<Prescription> result = prescriptionService.getAllPrescriptionByPatientId(id4);

        assertEquals(List.of(prescription, prescription1), result);


    }

}