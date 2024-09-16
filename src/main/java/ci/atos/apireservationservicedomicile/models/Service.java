package ci.atos.apireservationservicedomicile.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SERVICE")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String name;
    private String description;
    @Column(name = "prix")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "prestataire_id")
    private Provider provider;
}
