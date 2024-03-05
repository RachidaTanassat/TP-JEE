package ma.enset.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor @Data @AllArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name = "USER_NAME", unique = true, length = 20)
    private String userName;
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
   // @JoinTable(name = "USERS_ROLES")
    private List<Role> roles = new ArrayList<>();


    
}
