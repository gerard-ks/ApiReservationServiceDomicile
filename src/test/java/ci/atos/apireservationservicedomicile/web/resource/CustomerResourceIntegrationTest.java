package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.CustomerService;
import ci.atos.apireservationservicedomicile.services.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CustomerService customerService;


    @Test
    public void testGetCustomerById() {
        // Act
        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity("/api/clients/1", CustomerDTO.class);


        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isEqualTo(1L);
//        assertThat(response.getBody().getFirstName()).isEqualTo("John");
//        assertThat(response.getBody().getLastName()).isEqualTo("Doe");
    }


}
