package penta.sisPenta.gestaoFinanceira.Service;



import org.springframework.transaction.annotation.Transactional;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;
import penta.sisPenta.gestaoFinanceira.Model.Usuario;

import java.util.List;

public interface EmpresaService {



    @Transactional
    public Empresa salvar(Empresa empresa);


    @Transactional
    public Empresa atualizar_caso_nao_exista(Empresa empresa);


    @Transactional
    Empresa listar(Long id);

    @Transactional
    Boolean verificar_existencia(Long id);

    @Transactional
    Boolean verificar_existencia_cnpj(Long cnpj);
}
