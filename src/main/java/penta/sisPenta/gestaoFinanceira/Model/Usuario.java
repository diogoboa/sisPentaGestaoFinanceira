package penta.sisPenta.gestaoFinanceira.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import penta.sisPenta.gestaoFinanceira.Model.Status.UsuarioStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.UsuarioSexoStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "login", unique=true, nullable = false, length = 40)
    private String login;

    @Column(name = "senha", nullable = false, length = 300)
    private String senha;

    @Column(name = "nome", length = 350)
    private String nome;

    @Column(name = "nascimento")
    private Instant nascimento;

    @Column(name = "telefone_celular", length = 60)
    private String telefoneCelular;

    @Column(name = "email", length = 190)
    private String email;

    @Column(name = "rua", length = 350)
    private String rua;

    @Column(name = "numero", length = 50)
    private String numero;

    @Column(name = "complemento", length = 120)
    private String complemento;

    @Column(name = "bairro", length = 120)
    private String bairro;

    @Column(name = "cep", length = 40)
    private String cep;

    @Column(name = "referencia", length = 40)
    private String referencia;

    @Enumerated(EnumType.STRING)
    private UsuarioSexoStatus sexo;

    @Enumerated(EnumType.STRING)
    private UsuarioStatus status;


    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(name = "role_in_usuario",
            joinColumns = { @JoinColumn(name = "id_usuario") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") })
    private List<Role> role;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return role;

    }

    @Override
    public String getPassword() {

        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}