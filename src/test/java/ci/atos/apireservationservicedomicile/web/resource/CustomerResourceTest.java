package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.CustomerService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceTest {

    private CustomerService customerService;

    public CustomerResourceTest(CustomerService customerService) {
        this.customerService = customerService;
    }
}
