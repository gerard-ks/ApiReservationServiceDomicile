package ci.atos.apireservationservicedomicile.services;


import ci.atos.apireservationservicedomicile.services.dto.*;
import ci.atos.apireservationservicedomicile.web.exception.BookingNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;


import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingServiceRequestDTO bookingDTO) throws CustomerNotFoundException, BookingNotFoundException;
    BookingDTO updateBooking(Long id, BookingUpdateRequestDTO bookingDTO) throws BookingNotFoundException;
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getBookingsByClientId(Long clientId);
    List<BookingServiceDTO> getBookingsForProvider(Long providerId);
    List<BookingServiceDTO> getAllBookingsAndServices();
    List<BookingServiceDTO> getAllBookingsAndServicesByClientId(Long clientId);
}
