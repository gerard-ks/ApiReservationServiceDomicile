package ci.atos.apireservationservicedomicile.services.dto;


import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.models.enumerations.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private Genre gender;
    private UserDTO user;
}
