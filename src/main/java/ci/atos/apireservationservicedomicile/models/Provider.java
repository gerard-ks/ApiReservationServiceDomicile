package ci.atos.apireservationservicedomicile.models;

import ci.atos.apireservationservicedomicile.enumerations.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PRESTATAIRE")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String name;
    @Column(name = "telephone")
    private String phone;
    private String email;
    @Column(name = "adresse")
    private String address;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
}
