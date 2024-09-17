package ci.atos.apireservationservicedomicile.repositories;

import ci.atos.apireservationservicedomicile.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
