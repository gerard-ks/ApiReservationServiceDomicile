package ci.atos.apireservationservicedomicile.services.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private ProviderDTO provider;
    private CustomerDTO customer;
}
