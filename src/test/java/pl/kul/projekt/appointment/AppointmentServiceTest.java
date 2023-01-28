package pl.kul.projekt.appointment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kul.projekt.appointment.exceptions.AppointmentNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void shouldGetAllByDoctorId() {
        UUID appointmentId1 = UUID.randomUUID();
        UUID appointmentId2 = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        UUID patientId1 = UUID.randomUUID();
        UUID patientId2 = UUID.randomUUID();
        Appointment appointment1 = new Appointment(appointmentId1, doctorId, patientId1, LocalDate.MAX, LocalTime.NOON);
        Appointment appointment2 = new Appointment(appointmentId2, doctorId, patientId2, LocalDate.MAX, LocalTime.MIDNIGHT);

        when(appointmentRepository.getAllByDoctorId(doctorId)).thenReturn(List.of(appointment1, appointment2));
        List<Appointment> result = appointmentService.getAllByDoctorId(doctorId);

        assertEquals(List.of(appointment1, appointment2), result);
    }

    @Test
    void shouldGetAllAvailableByDoctorId() {
        UUID appointmentId1 = UUID.randomUUID();
        UUID appointmentId2 = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        Appointment appointment1 = new Appointment(appointmentId1, doctorId, null, LocalDate.MAX, LocalTime.NOON);
        Appointment appointment2 = new Appointment(appointmentId2, doctorId, null, LocalDate.MAX, LocalTime.MIDNIGHT);

        when(appointmentRepository.getAllByDoctorIdAndPatientIdIsNull(any())).thenReturn(List.of(appointment1, appointment2));
        List<Appointment> result = appointmentService.getAllAvailableByDoctorId(doctorId);

        assertEquals(List.of(appointment1, appointment2), result);
    }

    @Test
    void shouldGetAllFutureByPatientId() {
        UUID appointmentId1 = UUID.randomUUID();
        UUID appointmentId2 = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        Appointment appointment1 = new Appointment(appointmentId1, doctorId, patientId, LocalDate.MAX, LocalTime.NOON);
        Appointment appointment2 = new Appointment(appointmentId2, doctorId, patientId, LocalDate.MAX, LocalTime.MIDNIGHT);

        when(appointmentRepository.getAllByPatientIdAndDateAfter(any(), any())).thenReturn(List.of(appointment1, appointment2));
        List<Appointment> result = appointmentService.getAllFutureByPatientId(patientId);

        assertEquals(List.of(appointment1, appointment2), result);
    }

    @Test
    void shouldGetAllPastByPatientId() {
        UUID appointmentId1 = UUID.randomUUID();
        UUID appointmentId2 = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        Appointment appointment1 = new Appointment(appointmentId1, doctorId, patientId, LocalDate.MAX, LocalTime.NOON);
        Appointment appointment2 = new Appointment(appointmentId2, doctorId, patientId, LocalDate.MAX, LocalTime.MIDNIGHT);

        when(appointmentRepository.getAllByPatientIdAndDateBefore(any(), any())).thenReturn(List.of(appointment1, appointment2));
        List<Appointment> result = appointmentService.getAllPastByPatientId(patientId);

        assertEquals(List.of(appointment1, appointment2), result);
    }

    @Test
    void shouldSaveAppointment() {
        UUID appointmentId = UUID.randomUUID();
        UUID doctorId = UUID.randomUUID();
        Appointment appointment = new Appointment(appointmentId, doctorId, null, LocalDate.MAX, LocalTime.NOON);

        when(appointmentRepository.save(any())).thenReturn(appointment);
        Appointment result = appointmentService.add(appointment);

        assertEquals(appointment, result);
    }

    @Test
    void reserveShouldThrowAppointmentNotFoundException() {
        UUID appointmentId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();

        AppointmentNotFoundException result = assertThrows(AppointmentNotFoundException.class, () -> appointmentService.reserveAppointment(appointmentId, patientId));

        assertEquals("Appointment with id: " + appointmentId + " was not found", result.getMessage());
    }

    @Test
    void cancelShouldThrowAppointmentNotFoundException() {
        UUID appointmentId = UUID.randomUUID();

        AppointmentNotFoundException result = assertThrows(AppointmentNotFoundException.class, () -> appointmentService.reserveAppointment(appointmentId, null));

        assertEquals("Appointment with id: " + appointmentId + " was not found", result.getMessage());
    }

}
