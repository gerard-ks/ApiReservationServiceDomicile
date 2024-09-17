package ci.atos.apireservationservicedomicile.services;


import ci.atos.apireservationservicedomicile.services.dto.BookingDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.BookingNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;


import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingServiceRequestDTO bookingDTO) throws CustomerNotFoundException, BookingNotFoundException;
    BookingDTO updateBooking(Long id, BookingRequestDTO bookingDTO) throws BookingNotFoundException;
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getBookingsByClientId(Long clientId);
    List<BookingServiceDTO> getAllBookingsAndServices();
    List<BookingServiceDTO> getAllBookingsAndServicesByClientId(Long clientId);
}
