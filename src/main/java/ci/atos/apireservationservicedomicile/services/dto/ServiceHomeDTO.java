package ci.atos.apireservationservicedomicile.services.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceHomeDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private ProviderDTO provider;
    private ServiceHomeCategoryDTO category;
}
