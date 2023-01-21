package pl.kul.projekt.prescription;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public Prescription add(@RequestBody Prescription prescription) {
        return prescriptionService.add(prescription);
    }

    @GetMapping("/all")
    public List<Prescription> getAllPrescriptionByPatientId(@RequestParam("id") UUID id) {
        return prescriptionService.getAllPrescriptionByPatientId(id);
    }

    @DeleteMapping
    public void delete(@RequestBody Prescription prescription) {
        prescriptionService.delete(prescription);
    }

}
