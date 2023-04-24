package penta.sisPenta.gestaoFinanceira.Model.Dto.Usuario;


import lombok.Getter;
import lombok.Setter;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Role.RoleDtoGET;
import penta.sisPenta.gestaoFinanceira.Model.Role;
import penta.sisPenta.gestaoFinanceira.Model.Status.UsuarioSexoStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.UsuarioStatus;
import penta.sisPenta.gestaoFinanceira.Model.Usuario;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDtoGET {


    private Long idUsuario;
    private String login;
    private String nome;
    private Instant nascimento;
    private String telefoneCelular;
    private String email;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String referencia;

    private UsuarioSexoStatus sexo;

    private List<RoleDtoGET> role;


    private UsuarioStatus status;

    public UsuarioDtoGET() {
    }

    public UsuarioDtoGET(Usuario user) {
        this.idUsuario = user.getIdUsuario();
        this.login = user.getLogin();
        this.nome = user.getNome();
        this.nascimento = user.getNascimento();
        this.telefoneCelular = user.getTelefoneCelular();
        this.email = user.getEmail();
        this.rua = user.getRua();
        this.numero = user.getNumero();
        this.complemento = user.getComplemento();
        this.bairro = user.getBairro();
        this.cep = user.getCep();
        this.referencia = user.getReferencia();
        //this.role = new RoleDtoGET(user.getRole());
        this.role = new RoleDtoGET().listToDtoList(user.getRole());
        this.sexo = user.getSexo();
        this.status = user.getStatus();
    }

    public List<UsuarioDtoGET> toUsuarioDtoGET (List<Usuario> users)
    {
        List<UsuarioDtoGET> lista = new ArrayList<>();
        for(Usuario item : users){
            lista.add( new UsuarioDtoGET(item));
        }

        return lista;
    }




}
