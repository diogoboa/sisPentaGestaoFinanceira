package penta.sisPenta.gestaoFinanceira.Model.Dto.Empresa;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaSimplesPOST implements Serializable {

    private static final long serialVersionUID = 999L;

    @NotNull(message = "O campo CNPJ não pode ser vazio")
    private String cnpj;



    @NotNull(message = "Preencha o campo razão social")
    @Size(min=4, message = "Tamanho de razão social invalido, tamanho minimo = 4")
    private String razaoSocial;



    /*public Empresa to_Empresa() {

        Empresa empresa = new Empresa();
        empresa.setCnpj(Long.parseLong(cnpj));
        empresa.setRazaoSocial(razaoSocial.toUpperCase());
        empresa.setEmail("");
        empresa.setNomeEmpresarial("");
        empresa.setTelefone("");
        empresa.setLogadouro("");
        empresa.setNumero("");
        empresa.setBairro("");
        empresa.setComplemento("");
        empresa.setCidade("");
        empresa.setUf(EstadosSelecao.NENHUM_SELECIONADO);
        empresa.setPodeFaturar(false);
        empresa.setPodeDescontar(false);
        empresa.setUltimaAtualizacao(Instant.now());
        empresa.setEmpresaCadastroStatus(EmpresaCadastroStatus.PRE_CADASTRADO);

        return empresa;

    }*/


}
