package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.models.Role;
import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.models.enumerations.Genre;
import ci.atos.apireservationservicedomicile.repositories.CustomerRepository;
import ci.atos.apireservationservicedomicile.repositories.UserRepository;
import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import ci.atos.apireservationservicedomicile.services.dto.CustomerRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.RoleDTO;
import ci.atos.apireservationservicedomicile.services.dto.UserDTO;
import ci.atos.apireservationservicedomicile.services.mapper.CustomerMapper;
import ci.atos.apireservationservicedomicile.web.exception.CustomerNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;


import java.time.LocalDate;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomServiceImplUnitTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private Customer customer;
    private User user;
    private CustomerDTO customerDTO;
    private CustomerRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        user = createUser(2L, "john.doe@gmail.com", "John Doe", "ROLE_USER");
        customer = createCustomer(1L, "John", "Doe", "123456789", LocalDate.of(1990, 1, 1), "123 Main Street", Genre.HOMME, user);
        customerDTO = createCustomerDTO(customer, user);
        requestDTO = createCustomerRequestDTO(1L, customer);
    }

    private Customer createCustomer(Long id, String firstName, String lastName, String phone, LocalDate dob, String address, Genre gender, User user) {
        Customer c = new Customer();
        c.setId(id);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setPhone(phone);
        c.setDateOfBirth(dob);
        c.setAddress(address);
        c.setGender(gender);
        c.setUser(user);
        return c;
    }

    private User createUser(Long id, String email, String username, String roleName) {
        User u = new User();
        u.setId(id);
        u.setEmail(email);
        u.setUsername(username);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName(roleName);
        u.setRole(role);
        return u;
    }

    private CustomerDTO createCustomerDTO(Customer customer, User user) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(user.getRole(), roleDTO);
        userDTO.setRole(roleDTO);
        dto.setUser(userDTO);
        return dto;
    }

    private CustomerRequestDTO createCustomerRequestDTO(Long userId, Customer customer) {
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setUserId(userId);
        BeanUtils.copyProperties(customer, dto);
        return dto;
    }

    @Test
    public void whenFindByIdCustomer_thenReturnCustomerDto() throws CustomerNotFoundException {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerServiceImpl.findCustomerById(1L);

        assertCustomerDTO(result, customerDTO);
        verify(customerRepository).findById(1L);
        verify(customerMapper).toDto(customer);
    }

    private void assertCustomerDTO(CustomerDTO actual, CustomerDTO expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    public void givenWhenSaveCustomer_thenReturnCustomerDto() throws UserNotFoundException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerServiceImpl.createCustomer(requestDTO);

        assertCustomerDTO(result, customerDTO);
        verify(userRepository).findById(1L);
        verify(customerRepository).save(any(Customer.class));
        verify(customerMapper).toDto(customer);
    }
}
