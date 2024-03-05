package ma.enset.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@lombok.Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column(length = 50)
     private String name;
     @Temporal(TemporalType.DATE)
     private Date dateNaissance;
     private boolean malade;
     private int score;
     @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
     private Collection<RendezVous> rendezVous;
}
