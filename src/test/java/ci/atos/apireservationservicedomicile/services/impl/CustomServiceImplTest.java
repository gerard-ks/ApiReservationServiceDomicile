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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private final Long customerId = 1L;
    private Customer customer;
    private User user;
    private CustomerDTO customerDTO;
    private CustomerRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        // Initialiser un Customer
        customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhone("123456789");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        customer.setAddress("123 Main Street");
        customer.setGender(Genre.HOMME);

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("ROLE_USER");

        user = new User();
        user.setId(2L);
        user.setEmail("john.doe@gmail.com");
        user.setUsername("John Doe");
        user.setPassword("12345678"); // Pas besoin d'encoder dans le test
        user.setRole(role);

        customer.setUser(user);

        // Initialiser un CustomerDTO
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(roleDTO);

        customerDTO = new CustomerDTO();
        customerDTO.setId(customerId);
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setPhone("123456789");
        customerDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        customerDTO.setAddress("123 Main Street");
        customerDTO.setGender(Genre.HOMME);
        customerDTO.setUser(userDTO); // Mapping de l'utilisateur

        requestDTO = new CustomerRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setAddress("123 Main St");
        requestDTO.setPhone("555-1234");
        requestDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        requestDTO.setGender(Genre.HOMME);
    }

    @Test
    public void whenFindByIdCustomer_thenReturnCustomerDto() throws CustomerNotFoundException {
        // Définir les comportements des mocks
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        // Act
        CustomerDTO result = customerServiceImpl.findCustomerById(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123456789", result.getPhone());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDateOfBirth());
        assertEquals("123 Main Street", result.getAddress());
        assertEquals(Genre.HOMME, result.getGender());
        assertEquals(2L, result.getUser().getId());

        // Vérifier que les méthodes ont bien été appelées
        verify(customerRepository).findById(customerId);
        verify(customerMapper).toDto(customer);
    }

    @Test
    public void givenWhenSaveCustomer_thenReturnCustomerDto() throws UserNotFoundException {
        // Arrange


        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setUsername("johndoe");

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("USER");
        user.setRole(role);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstName("John");
        savedCustomer.setLastName("Doe");
        savedCustomer.setAddress("123 Main St");
        savedCustomer.setPhone("555-1234");
        savedCustomer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        savedCustomer.setGender(Genre.HOMME);
        savedCustomer.setUser(user);

        CustomerDTO expectedDTO = new CustomerDTO();
        expectedDTO.setId(1L);
        expectedDTO.setFirstName("John");
        expectedDTO.setLastName("Doe");
        expectedDTO.setAddress("123 Main St");
        expectedDTO.setPhone("555-1234");
        expectedDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        expectedDTO.setGender(Genre.HOMME);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("john.doe@example.com");
        userDTO.setUsername("johndoe");

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRoleName("USER");
        userDTO.setRole(roleDTO);

        expectedDTO.setUser(userDTO);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        when(customerMapper.toDto(savedCustomer)).thenReturn(expectedDTO);

        // Act
        CustomerDTO result = customerServiceImpl.createCustomer(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO.getId(), result.getId());
        assertEquals(expectedDTO.getFirstName(), result.getFirstName());
        assertEquals(expectedDTO.getLastName(), result.getLastName());
        assertEquals(expectedDTO.getAddress(), result.getAddress());
        assertEquals(expectedDTO.getPhone(), result.getPhone());
        assertEquals(expectedDTO.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(expectedDTO.getGender(), result.getGender());
        assertEquals(expectedDTO.getUser().getId(), result.getUser().getId());
        assertEquals(expectedDTO.getUser().getEmail(), result.getUser().getEmail());
        assertEquals(expectedDTO.getUser().getUsername(), result.getUser().getUsername());
        assertEquals(expectedDTO.getUser().getRole().getId(), result.getUser().getRole().getId());
        assertEquals(expectedDTO.getUser().getRole().getRoleName(), result.getUser().getRole().getRoleName());

        verify(userRepository).findById(1L);
        verify(customerRepository).save(any(Customer.class));
        verify(customerMapper).toDto(savedCustomer);
    }

}
