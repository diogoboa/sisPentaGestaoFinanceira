package penta.sisPenta.gestaoFinanceira.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.EntityNotFoundException;
import penta.sisPenta.gestaoFinanceira.Model.Usuario;
import penta.sisPenta.gestaoFinanceira.Repository.UsuarioRepository;
import penta.sisPenta.gestaoFinanceira.Service.UsuarioService;


import java.util.List;

@Service
public class UsuarioImplements implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;



    @Override
    public List<Usuario> listar_tudo() {

        return usuarioRepository.findAll();

    }

    @Override
    public Usuario listar_usuario(Long id) {

        return usuarioRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Usuario não Encontrado"));
    }

    /*@Override
    public LoggedDtoLogin verificarLogin(UsuarioDtoLogin usuarioDtoLogin) {


        Usuario usuario = usuarioRepository.findByUsername(usuarioDtoLogin.getUsuario()).orElseThrow( () -> new EntityNotFoundException("Usuario incorreto"));

        UsuarioDtoGET teste;
        if (passwordEncoder.matches(usuarioDtoLogin.getSenha(), usuario.getSenha()))
        {
            LoggedDtoLogin usuario_logado = new LoggedDtoLogin();
            usuario_logado.setCompany(new Company());
            usuario_logado.setUsuarioDtoGET(new UsuarioDtoGET(usuario));

            usuario_logado.getMapaDeListagemMenus().put("ordem_de_descarga_tipo_de_operacao", listagemDeMenusService.ordem_de_descarga_tipo_de_operacao());
            usuario_logado.getMapaDeListagemMenus().put("ordem_de_descarga_selecao_de_pagador", listagemDeMenusService.ordem_de_descarga_selecao_de_pagador());
            usuario_logado.getMapaDeListagemMenus().put("cadastro_usuario_status_de_atividade_possibilidades", listagemDeMenusService.cadastro_usuario_status_de_atividade_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("cadastro_usuario_regras_de_usuario_possibilidades", listagemDeMenusService.cadastro_usuario_regras_de_usuario_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("cadastro_doca_tipo_operacao_possibilidades", listagemDeMenusService.cadastro_doca_tipo_operacao_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("cadastro_doca_status_de_funcionamento_possibilidades", listagemDeMenusService.cadastro_doca_status_de_funcionamento_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("ocorrencia_tipo_possibilidades", listagemDeMenusService.ocorrencia_tipo_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("ocorrencia_causador_possibilidades", listagemDeMenusService.ocorrencia_causador_possibilidades());
            usuario_logado.getMapaDeListagemMenus().put("cobranca_forma_de_pagamento_possibilidades", listagemDeMenusService.cobranca_forma_de_pagamento_possibilidades());



            return usuario_logado;
        }
            //return new UsuarioDtoLoggedIn(usuario, new CompanyDtoLogin().padrao());

        else
            throw new EntityNotFoundException("Senha incorreta");


    }*/


    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }


    @Override
    public Usuario listar_usuario_por_username(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }



}
