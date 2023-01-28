package pl.kul.projekt.doctor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    void shouldSaveDoctor() {
        UUID id = UUID.randomUUID();
        Doctor doctor = new Doctor(id, "Name", "Surname", DoctorSpecialization.LARYNGOLOGIST);

        when(doctorRepository.save(any())).thenReturn(doctor);
        Doctor result = doctorService.add(doctor);

        assertEquals(doctor, result);
    }

    @Test
    void shouldReturnDoctorsBySpecialization() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Doctor doctor1 = new Doctor(id1, "Name1", "Surname1", DoctorSpecialization.LARYNGOLOGIST);
        Doctor doctor2 = new Doctor(id2, "Name2", "Surname2", DoctorSpecialization.LARYNGOLOGIST);

        when(doctorRepository.getAllBySpecialization(DoctorSpecialization.LARYNGOLOGIST))
                .thenReturn(List.of(doctor1, doctor2));
        List<Doctor> result = doctorService.getAllBySpecialization(DoctorSpecialization.LARYNGOLOGIST);

        assertEquals(List.of(doctor1, doctor2), result);
    }

    @Test
    void shouldReturnDoctor() {
        UUID id = UUID.randomUUID();
        Doctor doctor = new Doctor(id, "Name", "Surname", DoctorSpecialization.OCULIST);

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        Optional<Doctor> result = doctorService.get(id);

        assertEquals(doctor, result.get());
    }

}
