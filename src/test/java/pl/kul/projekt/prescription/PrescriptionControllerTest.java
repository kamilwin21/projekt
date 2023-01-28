package pl.kul.projekt.prescription;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PrescriptionController.class)
class PrescriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrescriptionService prescriptionService;

    @Test
    void shouldReturnAllPrescriptionsByPatientId() throws Exception {
        UUID prescriptionId = UUID.randomUUID();
        UUID prescriptionId1 = UUID.randomUUID();
        UUID appointmentId = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        UUID doctorId1 = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        LocalDate date = LocalDate.of(2023, 1, 17);

        Prescription prescription = new Prescription(prescriptionId, doctorId, appointmentId, patientId, "Lek 1", date);
        Prescription prescription1 = new Prescription(prescriptionId1, doctorId1, appointmentId, patientId, "Lek 2", date);

        when(prescriptionService.getAllPrescriptionByPatientId(patientId))
                .thenReturn(List.of(prescription, prescription1));


        this.mockMvc.perform(get("/prescription/all")
                        .param("id", String.valueOf(patientId)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(prescriptionId.toString()))
                .andExpect(jsonPath("$.[0].doctorId").exists())
                .andExpect(jsonPath("$.[0].doctorId").value(doctorId.toString()))
                .andExpect(jsonPath("$.[0].appointmentId").exists())
                .andExpect(jsonPath("$.[0].appointmentId").value(appointmentId.toString()))
                .andExpect(jsonPath("$.[0].patientId").exists())
                .andExpect(jsonPath("$.[0].patientId").value(patientId.toString()))
                .andExpect(jsonPath("$.[0].drugs").exists())
                .andExpect(jsonPath("$.[0].drugs").value("Lek 1"))
                .andExpect(jsonPath("$.[0].date").exists())
                .andExpect(jsonPath("$.[0].date").value(date.toString()))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(prescriptionId1.toString()))
                .andExpect(jsonPath("$.[1].doctorId").exists())
                .andExpect(jsonPath("$.[1].doctorId").value(doctorId1.toString()))
                .andExpect(jsonPath("$.[1].appointmentId").exists())
                .andExpect(jsonPath("$.[1].appointmentId").value(appointmentId.toString()))
                .andExpect(jsonPath("$.[1].patientId").exists())
                .andExpect(jsonPath("$.[1].patientId").value(patientId.toString()))
                .andExpect(jsonPath("$.[1].drugs").exists())
                .andExpect(jsonPath("$.[1].drugs").value("Lek 2"))
                .andExpect(jsonPath("$.[1].date").exists())
                .andExpect(jsonPath("$.[1].date").value(date.toString()));

    }

}
