package ci.atos.apireservationservicedomicile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String name;
    @Column(name = "telephone")
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(name = "adresse")
    private String address;
}
