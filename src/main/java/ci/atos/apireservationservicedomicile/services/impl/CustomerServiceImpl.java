package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.repositories.CustomerRepository;
import ci.atos.apireservationservicedomicile.services.CustomerService;
import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import ci.atos.apireservationservicedomicile.services.dto.CustomerRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.CustomerMapper;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
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
    public CustomerDTO createCustomer(CustomerRequestDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerRequestDTO customerDTO) throws CustomerNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found " + id));

        if(customerDTO.getName() != null) {
            existingCustomer.setName(customerDTO.getName());
        }

        if(customerDTO.getAddress() != null) {
            existingCustomer.setAddress(customerDTO.getAddress());
        }

        if(customerDTO.getPhone() != null) {
            existingCustomer.setPhone(customerDTO.getPhone());
        }

        if(customerDTO.getEmail() != null) {
            existingCustomer.setEmail(customerDTO.getEmail());
        }

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
