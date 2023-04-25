package penta.sisPenta.gestaoFinanceira.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.EntityNotFoundException;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Empresa.EmpresaSimplesPOST;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;
import penta.sisPenta.gestaoFinanceira.Model.Status.EmpresaCadastroStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.EmpresaTransmissaoStatus;
import penta.sisPenta.gestaoFinanceira.Model.Status.EstadosSelecao;
import penta.sisPenta.gestaoFinanceira.Repository.EmpresaRepository;
import penta.sisPenta.gestaoFinanceira.Service.EmpresaService;
import penta.sisPenta.gestaoFinanceira.Util.FuncoesDeValidacaoDeDados;
import penta.sisPenta.gestaoFinanceira.Util.ValidadorCPFeCNPJ;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaImplements implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;


    @Override
    @Transactional
    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);

    }

    @Override
    @Transactional
    public void sugerir_cadastro(EmpresaSimplesPOST empresaSimplesPOST) {
        Long cnpj = Long.parseLong(FuncoesDeValidacaoDeDados.somenteNumero(empresaSimplesPOST.getCnpj()));

        if(ValidadorCPFeCNPJ.isValidCNPJ(cnpj+"") || ValidadorCPFeCNPJ.isValidCPF(cnpj+""))
        {
            if(!(verificar_existencia_cnpj(cnpj)))
            {
                Empresa nova = new Empresa(cnpj, empresaSimplesPOST.getRazaoSocial(), null, null, null, null, null, null, null, null, false, false, Instant.now(), EmpresaTransmissaoStatus.TRANSMITIDO, EmpresaCadastroStatus.PRE_CADASTRADO, EstadosSelecao.NENHUM_SELECIONADO);
                salvar(nova);
            }
        }




    }




    @Override
    @Transactional
    public Empresa listar(Long id)
    {
        return empresaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não existe"));
    }


    @Override
    @Transactional
    public Boolean verificar_existencia(Long id)
    {
        return empresaRepository.existsById(id);
    }


    @Override
    @Transactional
    public Boolean verificar_existencia_cnpj(Long cnpj)
    {

        return empresaRepository.listar_por_cnpj(cnpj).isPresent();

    }




}
