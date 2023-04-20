package penta.sisPenta.gestaoFinanceira.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import penta.sisPenta.Model.Role;
import penta.sisPenta.Model.Usuario;
import penta.sisPenta.Repository.RoleRepository;
import penta.sisPenta.Repository.UsuarioRepository;

import java.util.List;


@Service
public class UserDetailsServiceImplements implements UserDetailsService {


    ;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado: " + username));

        List<Role> lista_de_autoridades = usuario.getRole();

        User user_spring = new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, lista_de_autoridades);

        return user_spring;

    }
}
