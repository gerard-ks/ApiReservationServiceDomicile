package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.CustomerService;
import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import ci.atos.apireservationservicedomicile.services.dto.CustomerRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getClientById(@PathVariable Long id) throws CustomerNotFoundException {
        CustomerDTO customer = customerService.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO) throws UserNotFoundException {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id, @RequestBody CustomerRequestDTO customerDTO) throws CustomerNotFoundException {
            CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
