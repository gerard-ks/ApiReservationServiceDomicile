package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
