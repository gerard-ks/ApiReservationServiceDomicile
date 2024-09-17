package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.ServiceHomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceHomeCategoryRepository extends JpaRepository<ServiceHomeCategory, Long> {
}
