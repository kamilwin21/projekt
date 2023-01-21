package pl.kul.projekt.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public Doctor add(@RequestBody Doctor doctor) {
        return doctorService.add(doctor);
    }

    @DeleteMapping
    public void delete(@RequestBody Doctor doctor) {
        doctorService.delete(doctor);
    }

    @GetMapping("/all")
    public List<Doctor> getAllBySpecialization(@RequestParam("specialization") DoctorSpecialization specialization) {
        return doctorService.getAllBySpecialization(specialization);
    }


}
