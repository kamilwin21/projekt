package pl.kul.projekt.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kul.projekt.patient.exceptions.InvalidEmailException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public Optional<Patient> get(@RequestParam("id") UUID id) {
        return patientService.get(id);
    }

    @PostMapping
    public Patient add(@RequestBody Patient patient) throws InvalidEmailException {
        return patientService.add(patient);
    }

    @DeleteMapping
    public void delete(@RequestBody Patient patient) {
        patientService.delete(patient);
    }
}
