package penta.sisPenta.gestaoFinanceira.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.EntityNotFoundException;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Empresa.EmpresaSimplesPOST;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;
import penta.sisPenta.gestaoFinanceira.Repository.EmpresaRepository;
import penta.sisPenta.gestaoFinanceira.Service.EmpresaService;

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
    public Empresa criar_caso_nao_exista(EmpresaSimplesPOST empresaSimplesPOST) {

        if(verificar_existencia(empresaSimplesPOST.getCnpj()))

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
        return empresaRepository.existsById(id);
    }




}
