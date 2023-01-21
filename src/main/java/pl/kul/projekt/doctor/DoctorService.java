package pl.kul.projekt.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor add(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    public List<Doctor> getAllBySpecialization(DoctorSpecialization specialization) {
        return doctorRepository.getAllBySpecialization(specialization);
    }
}
