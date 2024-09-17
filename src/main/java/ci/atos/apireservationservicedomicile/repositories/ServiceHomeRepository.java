package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.ServiceHome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ServiceHomeRepository extends JpaRepository<ServiceHome, Long> {
    List<ServiceHome> findByCategoryId(Long categoryId);
    List<ServiceHome> findAllById(Iterable<Long> ids);
}
