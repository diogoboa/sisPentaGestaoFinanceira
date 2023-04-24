package penta.sisPenta.gestaoFinanceira.Model.Dto.Role;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import penta.sisPenta.gestaoFinanceira.Model.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDtoGET {

    private String chaveRegra;

    private String regraDescrita;

    private String descricao;

    private String papeis;




    public RoleDtoGET(Role role) {
        this.chaveRegra = role.getChaveRegra();
        this.regraDescrita = role.getRegraDescrita();
        this.descricao = role.getDescricao();
        this.papeis = role.getPapeis();
    }

    public List<RoleDtoGET> listToDtoList (List<Role> roles)
    {
        if(roles.isEmpty())
            return new ArrayList<>();
        List<RoleDtoGET> lista_convertida = new ArrayList<>();
        for(Role regra_encontrada : roles)
        {
            lista_convertida.add(new RoleDtoGET(regra_encontrada));
        }
        return lista_convertida;
    }


}
