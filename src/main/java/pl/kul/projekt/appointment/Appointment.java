package pl.kul.projekt.appointment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Appointment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private UUID doctorId;
    private UUID patientId;
    private LocalDate date;
    private LocalTime time;

    public Appointment(UUID doctorId, LocalDate date, LocalTime time) {
        id = UUID.randomUUID();
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }
}
