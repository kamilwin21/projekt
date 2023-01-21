package pl.kul.projekt.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kul.projekt.appointment.exceptions.AppointmentNotFoundException;
import pl.kul.projekt.appointment.file.Parser;

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
    private final Parser parser;

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

    public Appointment reserveAppointment(UUID appointmentId, UUID patientId) throws AppointmentNotFoundException {
        return updateAppointment(appointmentId, patientId);
    }

    public Appointment cancelAppointment(UUID appointmentId) throws AppointmentNotFoundException {
        return updateAppointment(appointmentId, null);
    }

    public List<Appointment> addFromFile(MultipartFile file) throws IOException {
        return parser.parse(new String(file.getBytes(), StandardCharsets.UTF_8));
    }

    private Appointment updateAppointment(UUID appointmentId, UUID patientId) throws AppointmentNotFoundException {
        Appointment newAppointment;
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            newAppointment = new Appointment(
                    appointment.get().getId(),
                    appointment.get().getDoctorId(),
                    patientId,
                    appointment.get().getDate(),
                    appointment.get().getTime()
            );
            return appointmentRepository.save(newAppointment);
        } else {
            throw new AppointmentNotFoundException("Appointment with id:" + appointmentId + "was not found");
        }
    }

}
