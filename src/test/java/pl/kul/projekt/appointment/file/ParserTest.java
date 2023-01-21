package pl.kul.projekt.appointment.file;

import org.junit.jupiter.api.Test;
import pl.kul.projekt.appointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void shouldReturnListOfAppointments() {
        List<Appointment> appointments = List.of(
                new Appointment(
                        UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),
                        LocalDate.of(2022, 1, 1),
                        LocalTime.of(16, 0)),
                new Appointment(
                        UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),
                        LocalDate.of(2022, 1, 2),
                        LocalTime.of(17, 0)),
                new Appointment(
                        UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),
                        LocalDate.of(2022, 1, 3),
                        LocalTime.of(18, 0))
        );
        String content = """
                3fa85f64-5717-4562-b3fc-2c963f66afa6,2022-01-01,16:00
                3fa85f64-5717-4562-b3fc-2c963f66afa6,2022-01-02,17:00
                3fa85f64-5717-4562-b3fc-2c963f66afa6,2022-01-03,18:00""";

        List<Appointment> results = parser.parse(content);

        assertEquals(appointments, results);
    }

}
