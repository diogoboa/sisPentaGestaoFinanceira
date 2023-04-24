package penta.sisPenta.gestaoFinanceira.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import penta.sisPenta.gestaoFinanceira.Model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query("SELECT u FROM Usuario u WHERE u.login = ?1")
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.login = ?1 AND u.senha = ?2")
    Optional<Usuario> findByUsernameAndPassword(String username, String password);




}
