package ci.atos.apireservationservicedomicile.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "EVALUATION")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note")
    private int rating;
    @Column(name = "commentaire")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "prestataire_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer customer;
}
