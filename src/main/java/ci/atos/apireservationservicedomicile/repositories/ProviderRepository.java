package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT DISTINCT s.provider FROM ServiceHome s WHERE s.name LIKE %:serviceName%")
    List<Provider> findProvidersByServiceName(@Param("serviceName") String serviceName);

    @Query("SELECT DISTINCT s.provider FROM ServiceHome s WHERE s.category.name LIKE %:categoryName%")
    List<Provider> findProvidersByCategoryName(@Param("categoryName") String categoryName);
}
