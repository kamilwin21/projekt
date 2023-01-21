package pl.kul.projekt.appointment.file;

import org.springframework.stereotype.Component;
import pl.kul.projekt.appointment.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class Parser {

    public List<Appointment> parse(String content) {
        List<Appointment> appointments = new ArrayList<>();

        String[] lines = content.split("\n");
        Arrays.stream(lines).forEach(l -> {
            String[] line = l.split(",");
            try {
                appointments.add(
                        new Appointment(UUID.fromString(line[0]),
                                LocalDate.parse(line[1]),
                                LocalTime.parse(line[2]))
                );
            } catch (Exception e) {
                System.out.println("Could not parse");
            }
        });

        return appointments;
    }

}
