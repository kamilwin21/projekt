package pl.kul.projekt.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.kul.projekt.appointment.exceptions.AppointmentNotFoundException;
import pl.kul.projekt.patient.exceptions.PatientNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all/available")
    public List<Appointment> getAllAvailableByDoctorId(@RequestParam("doctorId") UUID id) {
        return appointmentService.getAllAvailableByDoctorId(id);
    }

    @GetMapping("/all")
    public List<Appointment> getAllByDoctorId(@RequestParam("doctorId") UUID id) {
        return appointmentService.getAllByDoctorId(id);
    }

    @GetMapping("/all/future")
    public List<Appointment> getAllFutureByPatientId(@RequestParam("patientId") UUID id) {
        return appointmentService.getAllFutureByPatientId(id);
    }

    @GetMapping("/all/past")
    public List<Appointment> getAllPastByPatientId(@RequestParam("patientId") UUID id) {
        return appointmentService.getAllPastByPatientId(id);
    }

    @PostMapping
    public Appointment add(@RequestBody Appointment appointment) {
        return appointmentService.add(appointment);
    }

    @PostMapping("/file")
    public List<Appointment> addFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return appointmentService.addFromFile(file);
    }

    @PutMapping("/reserve")
    public Appointment reserve(@RequestParam("appointmentId") UUID appointmentId,
                               @RequestParam("patientId") UUID patientId)
            throws AppointmentNotFoundException, PatientNotFoundException {
        return appointmentService.reserveAppointment(appointmentId, patientId);
    }

    @PutMapping("/cancel")
    public Appointment cancel(@RequestParam("appointmentId") UUID appointmentId)
            throws AppointmentNotFoundException, PatientNotFoundException {
        return appointmentService.cancelAppointment(appointmentId);
    }

}
