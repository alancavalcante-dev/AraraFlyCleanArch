package br.com.alanpcavalcante.araraflyapi.infrastructure.model;


import br.com.alanpcavalcante.araraflyapi.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity(name = "tbl_users")
@Table(name = "tbl_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String login;
    private String password;
    private Boolean isDeveloper;
    private UserRole role;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_profile")
    @ToString.Exclude
    private ProfileEntity profile;

    public UserEntity(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

}