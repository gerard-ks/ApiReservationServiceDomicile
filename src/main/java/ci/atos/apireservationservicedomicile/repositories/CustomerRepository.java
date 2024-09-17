package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
