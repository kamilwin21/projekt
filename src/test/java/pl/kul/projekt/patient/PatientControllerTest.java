package pl.kul.projekt.patient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService patientService;

    @Test
    void shouldReturnPatientById() throws Exception {
        UUID id = UUID.randomUUID();
        Patient patient = new Patient(id, "Name", "Surname", "patient@gmail.com");

        when(patientService.get(id)).thenReturn(Optional.of(patient));

        this.mockMvc.perform(get("/patient").param("id", String.valueOf(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.surname").exists())
                .andExpect(jsonPath("$.surname").value("Surname"))
                .andExpect(jsonPath("$.email").exists());
    }
}