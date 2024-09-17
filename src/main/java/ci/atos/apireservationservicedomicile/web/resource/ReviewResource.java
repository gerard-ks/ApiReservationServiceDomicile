package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.ReviewService;
import ci.atos.apireservationservicedomicile.services.dto.ReviewDTO;
import ci.atos.apireservationservicedomicile.services.dto.ReviewRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.ClientNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewResource {

    private final ReviewService reviewService;

    public ReviewResource(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@Valid @RequestBody ReviewRequestDTO reviewDTO) throws ClientNotFoundException, MyProviderNotFoundException {
            ReviewDTO createdReview = reviewService.addReview(reviewDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }
}
