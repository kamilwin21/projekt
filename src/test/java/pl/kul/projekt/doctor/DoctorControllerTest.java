package pl.kul.projekt.doctor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    void shouldReturnDoctorById() throws Exception {
        UUID id = UUID.randomUUID();
        Doctor doctor = new Doctor(id, "Name", "Surname", DoctorSpecialization.LARYNGOLOGIST);

        when(doctorService.get(id)).thenReturn(Optional.of(doctor));

        this.mockMvc.perform(get("/doctor").param("id", String.valueOf(id))).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.surname").exists())
                .andExpect(jsonPath("$.surname").value("Surname"))
                .andExpect(jsonPath("$.specialization").exists())
                .andExpect(jsonPath("$.specialization").value(DoctorSpecialization.LARYNGOLOGIST.name()));
    }

    @Test
    void shouldReturnDoctorsBySpecialization() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Doctor doctor1 = new Doctor(id1, "Name1", "Surname1", DoctorSpecialization.LARYNGOLOGIST);
        Doctor doctor2 = new Doctor(id2, "Name2", "Surname2", DoctorSpecialization.LARYNGOLOGIST);

        when(doctorService.getAllBySpecialization(DoctorSpecialization.LARYNGOLOGIST))
                .thenReturn(List.of(doctor1, doctor2));

        this.mockMvc.perform(get("/doctor/all")
                        .param("specialization", DoctorSpecialization.LARYNGOLOGIST.name()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(id1.toString()))
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].name").value("Name1"))
                .andExpect(jsonPath("$.[0].surname").exists())
                .andExpect(jsonPath("$.[0].surname").value("Surname1"))
                .andExpect(jsonPath("$.[0].specialization").exists())
                .andExpect(jsonPath("$.[0].specialization").value(DoctorSpecialization.LARYNGOLOGIST.name()))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(id2.toString()))
                .andExpect(jsonPath("$.[1].name").exists())
                .andExpect(jsonPath("$.[1].name").value("Name2"))
                .andExpect(jsonPath("$.[1].surname").exists())
                .andExpect(jsonPath("$.[1].surname").value("Surname2"))
                .andExpect(jsonPath("$.[1].specialization").exists())
                .andExpect(jsonPath("$.[1].specialization").value(DoctorSpecialization.LARYNGOLOGIST.name()));
    }

}
