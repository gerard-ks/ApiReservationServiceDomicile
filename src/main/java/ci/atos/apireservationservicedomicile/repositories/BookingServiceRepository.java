package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.BookingServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingServiceRepository extends JpaRepository<BookingServiceModel, Long> {
    List<BookingServiceModel> findByBooking_Customer_Id(Long customerId);
    List<BookingServiceModel> findByServiceHome_Provider_Id(Long providerId);
}
