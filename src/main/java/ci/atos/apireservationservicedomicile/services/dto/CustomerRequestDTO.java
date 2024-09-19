package ci.atos.apireservationservicedomicile.services.dto;

import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.models.enumerations.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank(message = "Le nom est requis")
    private String firstName;

    @NotBlank(message = "Le prenom est requis")
    private String lastName;

    @NotBlank(message = "Le numéro de téléphone est requis")
    private String phone;

    @NotNull(message = "La date de naissance est requis")
    private LocalDate dateOfBirth;

    @NotBlank(message = "L'adresse est requis")
    private String address;

    @NotNull(message = "Le genre est requis")
    private Genre gender;

    @NotNull(message = "L'utilisateur est requis")
    private Long userId;
}
