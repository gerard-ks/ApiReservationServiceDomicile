package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
}
