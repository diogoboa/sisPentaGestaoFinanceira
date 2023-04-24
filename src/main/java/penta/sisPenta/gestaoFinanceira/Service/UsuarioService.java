package penta.sisPenta.gestaoFinanceira.Service;



import penta.sisPenta.gestaoFinanceira.Model.Usuario;

import java.util.List;

public interface UsuarioService {


    public List<Usuario> listar_tudo();

    public Usuario listar_usuario(Long id);



    public Usuario salvar(Usuario usuario);


    public Usuario listar_usuario_por_username(String username);


}
