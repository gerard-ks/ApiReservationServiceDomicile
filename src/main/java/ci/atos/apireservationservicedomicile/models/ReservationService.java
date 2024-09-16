package ci.atos.apireservationservicedomicile.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RESERVATIONSERVICE")
public class ReservationService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
