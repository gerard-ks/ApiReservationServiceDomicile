package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.enumerations.BookingStatus;
import ci.atos.apireservationservicedomicile.models.Booking;
import ci.atos.apireservationservicedomicile.models.BookingServiceModel;
import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.models.ServiceHome;
import ci.atos.apireservationservicedomicile.repositories.BookingRepository;
import ci.atos.apireservationservicedomicile.repositories.BookingServiceRepository;
import ci.atos.apireservationservicedomicile.repositories.CustomerRepository;
import ci.atos.apireservationservicedomicile.repositories.ServiceHomeRepository;
import ci.atos.apireservationservicedomicile.services.BookingService;
import ci.atos.apireservationservicedomicile.services.dto.BookingDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceDTO;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.BookingMapper;
import ci.atos.apireservationservicedomicile.services.mapper.BookingServiceMapper;
import ci.atos.apireservationservicedomicile.web.exception.BookingNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final CustomerRepository customerRepository;
    private final ServiceHomeRepository serviceHomeRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingServiceRepository bookingServiceRepository;
    private final BookingServiceMapper bookingServiceMapper;

    public BookingServiceImpl(CustomerRepository customerRepository, ServiceHomeRepository serviceHomeRepository, BookingRepository bookingRepository, BookingMapper bookingMapper, BookingServiceRepository serviceRepository, BookingServiceMapper bookingServiceMapper) {
        this.customerRepository = customerRepository;
        this.serviceHomeRepository = serviceHomeRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingServiceRepository = serviceRepository;
        this.bookingServiceMapper = bookingServiceMapper;
    }

    @Override
    public BookingDTO createBooking(BookingServiceRequestDTO bookingDTO) throws CustomerNotFoundException, BookingNotFoundException {

        // Vérifier l'existence des services
        List<Long> serviceIds = bookingDTO.getListServiceId();
        List<ServiceHome> existingServices = serviceHomeRepository.findAllById(serviceIds);

        // Vérifier si tous les services existent
        if (existingServices.size() != serviceIds.size()) {
            // Trouver les IDs des services manquants
            List<Long> missingServiceIds = serviceIds.stream()
                    .filter(id -> existingServices.stream().noneMatch(service -> service.getId().equals(id)))
                    .toList();
            throw new BookingNotFoundException("Services not found: " + missingServiceIds);
        }

        Customer existingCustomer = customerRepository.findById(bookingDTO.getBooking().getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("Customer " + bookingDTO.getBooking().getCustomerId() + " not found"));

        Booking booking = new Booking();
        booking.setStatus(BookingStatus.PENDING);
        booking.setCustomer(existingCustomer);
        booking.setBookingTime(bookingDTO.getBooking().getBookingTime());
        Booking savedBooking = bookingRepository.save(booking);

        // Associer les services à la réservation
        for (Long serviceId : serviceIds) {
            ServiceHome serviceHome = existingServices.stream()
                    .filter(service -> service.getId().equals(serviceId))
                    .findFirst()
                    .orElseThrow(() -> new BookingNotFoundException("Service " + serviceId + " not found"));

            BookingServiceModel bookingServiceModel = new BookingServiceModel();
            bookingServiceModel.setBooking(savedBooking);
            bookingServiceModel.setServiceHome(serviceHome);
            bookingServiceRepository.save(bookingServiceModel);
        }

        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingRequestDTO bookingDTO) throws BookingNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Booking existingBooking = bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException("Booking not found"));
        existingBooking.setStatus(bookingDTO.getStatus());
        Booking savedBooking = bookingRepository.save(existingBooking);

        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public List<BookingDTO> getBookingsByClientId(Long clientId) {

        if (clientId == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        return bookingRepository.findByCustomerId(clientId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public List<BookingServiceDTO> getAllBookingsAndServices() {

        List<BookingServiceModel> models = bookingServiceRepository.findAll();
        log.debug("Booking services found: {}", models);

        return models
                .stream()
                .map(bookingServiceMapper::toDto)
                .toList();
    }

    @Override
    public List<BookingServiceDTO> getAllBookingsAndServicesByClientId(Long clientId) {


        if (clientId == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        return bookingServiceRepository.findByBooking_Customer_Id(clientId)
                .stream()
                .map(bookingServiceMapper::toDto)
                .toList();
    }
}
