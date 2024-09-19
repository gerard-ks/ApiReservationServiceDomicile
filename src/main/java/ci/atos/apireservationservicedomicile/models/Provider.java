package ci.atos.apireservationservicedomicile.models;

import ci.atos.apireservationservicedomicile.models.enumerations.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRESTATAIRE")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String firstName;

    @Column(name = "prenom")
    private String lastName;

    @Column(name = "telephone")
    private String phone;

    @Column(name = "date_de_naissance")
    private LocalDate dateOfBirth;

    @Column(name = "adresse")
    private String address;

    @Column(name = "sexe")
    private Genre gender;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;
}
