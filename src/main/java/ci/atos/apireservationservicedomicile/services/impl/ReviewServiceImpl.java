package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Customer;
import ci.atos.apireservationservicedomicile.models.Provider;
import ci.atos.apireservationservicedomicile.models.Review;
import ci.atos.apireservationservicedomicile.repositories.CustomerRepository;
import ci.atos.apireservationservicedomicile.repositories.ProviderRepository;
import ci.atos.apireservationservicedomicile.repositories.ReviewRepository;
import ci.atos.apireservationservicedomicile.services.ReviewService;
import ci.atos.apireservationservicedomicile.services.dto.ReviewDTO;
import ci.atos.apireservationservicedomicile.services.dto.ReviewRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.ReviewMapper;
import ci.atos.apireservationservicedomicile.web.exception.ClientNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ProviderRepository providerRepository;
    private final CustomerRepository customerRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ProviderRepository providerRepository, CustomerRepository customerRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.providerRepository = providerRepository;
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDTO addReview(ReviewRequestDTO reviewDTO) throws MyProviderNotFoundException, ClientNotFoundException {

        Provider existingProvider = providerRepository.findById(reviewDTO.getProviderId()).orElseThrow(()-> new MyProviderNotFoundException("Provider " + reviewDTO.getProviderId() + " not found"));
        Customer existingCustomer = customerRepository.findById(reviewDTO.getCustomerId()).orElseThrow(()-> new ClientNotFoundException("Client " + reviewDTO.getCustomerId() + " not found"));

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setProvider(existingProvider);
        review.setCustomer(existingCustomer);

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toDto(savedReview);
    }
}
