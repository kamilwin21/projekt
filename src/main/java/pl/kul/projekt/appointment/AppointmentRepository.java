package pl.kul.projekt.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> getAllByDoctorId(UUID id);

    List<Appointment> getAllByDoctorIdAndPatientIdIsNull(UUID id);

    List<Appointment> getAllByPatientIdAndDateBefore(UUID patientId, LocalDate date);

    List<Appointment> getAllByPatientIdAndDateAfter(UUID patientId, LocalDate date);

}
