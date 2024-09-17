package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.ReviewDTO;
import ci.atos.apireservationservicedomicile.services.dto.ReviewRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.ClientNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;

public interface ReviewService {
    ReviewDTO addReview(ReviewRequestDTO reviewDTO) throws MyProviderNotFoundException, ClientNotFoundException;
}
