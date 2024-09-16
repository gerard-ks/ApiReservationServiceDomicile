package ci.atos.apireservationservicedomicile.models;

import ci.atos.apireservationservicedomicile.enumerations.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateReservation")
    private LocalDateTime bookingTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "statutReservation")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer customer;
}
