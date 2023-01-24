package pl.kul.projekt.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kul.projekt.appointment.exceptions.AppointmentNotFoundException;
import pl.kul.projekt.appointment.file.Parser;
import pl.kul.projekt.appointment.mailer.Mailer;
import pl.kul.projekt.patient.PatientService;
import pl.kul.projekt.patient.exceptions.PatientNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final Parser parser;
    private final Mailer mailer;

    public List<Appointment> getAllAvailableByDoctorId(UUID id) {
        return appointmentRepository.getAllByDoctorIdAndPatientIdIsNull(id);
    }

    public List<Appointment> getAllByDoctorId(UUID id) {
        return appointmentRepository.getAllByDoctorId(id);
    }

    public List<Appointment> getAllPastByPatientId(UUID id) {
        return appointmentRepository.getAllByPatientIdAndDateBefore(id, LocalDate.now());
    }

    public List<Appointment> getAllFutureByPatientId(UUID id) {
        return appointmentRepository.getAllByPatientIdAndDateAfter(id, LocalDate.now().minusDays(1));
    }

    public Appointment add(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment reserveAppointment(UUID appointmentId, UUID patientId)
            throws AppointmentNotFoundException, PatientNotFoundException {
        return updateAppointment(appointmentId, patientId);
    }

    public Appointment cancelAppointment(UUID appointmentId)
            throws AppointmentNotFoundException, PatientNotFoundException {
        return updateAppointment(appointmentId, null);
    }

    public List<Appointment> addFromFile(MultipartFile file) throws IOException {
        return appointmentRepository.saveAll(parser.parse(new String(file.getBytes(), StandardCharsets.UTF_8)));
    }

    private Appointment updateAppointment(UUID appointmentId, UUID patientId)
            throws AppointmentNotFoundException, PatientNotFoundException {
        Appointment newAppointment;
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            String recipient =
                    patientService.getPatientEmail(patientId != null ? patientId : appointment.get().getPatientId());
            String subject = "Akutalizacja statusu wizyty";
            String body = patientId == null
                    ? "Wizyta o identyfikatorze " + appointmentId + " została odwołana."
                    : "Potwierdzenie nowej wizyty o identyfikatorze " + appointmentId;
            newAppointment = new Appointment(
                    appointment.get().getId(),
                    appointment.get().getDoctorId(),
                    patientId,
                    appointment.get().getDate(),
                    appointment.get().getTime()
            );

            mailer.getMailer().send(mailer.getMessage(recipient, subject, body));
            return appointmentRepository.save(newAppointment);
        } else {
            throw new AppointmentNotFoundException("Appointment with id: " + appointmentId + " was not found");
        }
    }

}
