package ma.enset.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "ROLES")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<User> users= new ArrayList<>();
}
