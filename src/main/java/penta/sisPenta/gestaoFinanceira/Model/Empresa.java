package penta.sisPenta.gestaoFinanceira.Model;

import jakarta.persistence.*;
import lombok.*;
import penta.sisPenta.gestaoFinanceira.Model.Status.EmpresaCadastroStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.EmpresaTransmissaoStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.EstadosSelecao;


import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @Column(name = "cnpj")
    private Long cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "email")
    private String email;

    @Column(name = "nome_empresarial")
    private String nomeEmpresarial;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "logadouro")
    private String logadouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cidade")
    private String cidade;


    @Column(name = "pode_faturar")
    private Boolean podeFaturar;

    @Column(name = "pode_descontar")
    private Boolean podeDescontar;


    @Column(name = "ultima_atualizacao")
    private Instant ultimaAtualizacao;


    @Enumerated(EnumType.STRING)
    private EmpresaTransmissaoStatus transmissao;

    @Enumerated(EnumType.STRING)
    private EmpresaCadastroStatus empresaCadastroStatus;

    @Enumerated(EnumType.STRING)
    private EstadosSelecao uf;




}