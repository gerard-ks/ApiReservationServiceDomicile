package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import ci.atos.apireservationservicedomicile.services.dto.CustomerRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    CustomerDTO createCustomer(CustomerRequestDTO customerDTO) throws UserNotFoundException;
    CustomerDTO updateCustomer(Long id, CustomerRequestDTO customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> findAllCustomers();
}
