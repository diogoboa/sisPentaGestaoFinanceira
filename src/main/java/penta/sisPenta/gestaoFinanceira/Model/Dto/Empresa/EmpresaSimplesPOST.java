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




    private String cnpj;

    private String razaoSocial;



}
