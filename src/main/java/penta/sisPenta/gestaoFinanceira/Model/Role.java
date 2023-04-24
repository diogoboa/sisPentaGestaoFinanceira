package penta.sisPenta.gestaoFinanceira.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @Column(name = "id_role", nullable = false)
    private Long idRole;


    //@OneToMany(mappedBy="id.idRole", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    //private List<RoleInUsuario> roleInUsuario;


    @Column(name = "chave_regra", nullable = false, length = 220)
    private String chaveRegra;

    @Column(name = "regra_descrita", nullable = false, length = 220)
    private String regraDescrita;

    @Column(name = "descricao", nullable = false, length = 400)
    private String descricao;


    @Column(name = "papeis", nullable = false, length = 400)
    private String papeis;






    @Override
    public String getAuthority() {
        return chaveRegra;
    }
}
