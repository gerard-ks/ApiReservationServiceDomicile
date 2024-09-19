package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.repositories.CustomerRepository;
import ci.atos.apireservationservicedomicile.repositories.UserRepository;
import ci.atos.apireservationservicedomicile.services.CustomerService;
import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import ci.atos.apireservationservicedomicile.services.dto.CustomerRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.CustomerMapper;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found " + id));
        return customerMapper.toDto(existingCustomer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO customerDTO) throws UserNotFoundException {

        User user = userRepository.findById(customerDTO.getUserId()).orElseThrow(()-> new UserNotFoundException("User " + customerDTO.getUserId() + " not found"));
        Customer savedCustomer = getSavedCustomer(customerDTO, user);
        return customerMapper.toDto(savedCustomer);
    }

    private Customer getSavedCustomer(CustomerRequestDTO customerDTO, User user) {
        log.debug("function getSavedCustomer");
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setGender(customerDTO.getGender());
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerRequestDTO customerDTO) throws CustomerNotFoundException {
        // Validation de l'ID du client
        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        // Recherche du client existant
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found " + id));

        // Mise à jour des champs
        if (customerDTO.getFirstName() != null) {
            existingCustomer.setFirstName(customerDTO.getFirstName());
        }

        if (customerDTO.getLastName() != null) {
            existingCustomer.setLastName(customerDTO.getLastName());
        }

        if (customerDTO.getAddress() != null) {
            existingCustomer.setAddress(customerDTO.getAddress());
        }

        if (customerDTO.getPhone() != null) {
            existingCustomer.setPhone(customerDTO.getPhone());
        }

        if (customerDTO.getDateOfBirth() != null) {
            existingCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        }

        if (customerDTO.getGender() != null) {
            existingCustomer.setGender(customerDTO.getGender());
        }

        // Sauvegarde et retour du client mis à jour
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found " + id));
        customerRepository.delete(existingCustomer);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }
}
