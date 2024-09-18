package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.BookingService;
import ci.atos.apireservationservicedomicile.services.dto.*;
import ci.atos.apireservationservicedomicile.web.exception.BookingNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingResource {

    private final BookingService bookingService;

    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingServiceRequestDTO bookingDTO) throws BookingNotFoundException, CustomerNotFoundException {
            BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingUpdateRequestDTO bookingDTO) throws BookingNotFoundException {
            BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
            return ResponseEntity.ok(updatedBooking);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByClientId(@PathVariable Long clientId) {
        List<BookingDTO> bookings = bookingService.getBookingsByClientId(clientId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/services")
    public ResponseEntity<List<BookingServiceDTO>> getAllBookingsAndServices() {
        List<BookingServiceDTO> bookingsAndServices = bookingService.getAllBookingsAndServices();
        return ResponseEntity.ok(bookingsAndServices);
    }

    @GetMapping("/services/clients/{clientId}")
    public ResponseEntity<List<BookingServiceDTO>> getAllBookingsAndServicesByClientId(@PathVariable Long clientId) {
        List<BookingServiceDTO> bookingsAndServices = bookingService.getAllBookingsAndServicesByClientId(clientId);
        return ResponseEntity.ok(bookingsAndServices);
    }

    @GetMapping("/providers/{providerId}")
    public ResponseEntity<List<BookingServiceDTO>> getBookingsForProvider(@PathVariable Long providerId) {
        List<BookingServiceDTO> bookings = bookingService.getBookingsForProvider(providerId);
        return ResponseEntity.ok(bookings);
    }

}
