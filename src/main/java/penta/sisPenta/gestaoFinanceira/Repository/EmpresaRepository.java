package penta.sisPenta.gestaoFinanceira.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;
import penta.sisPenta.gestaoFinanceira.Model.Role;
import penta.sisPenta.gestaoFinanceira.Model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository  extends JpaRepository<Empresa, Long> {



    @Query("SELECT u FROM Empresa u WHERE u.cnpj = :cnpj")
    Optional<Empresa> listar_por_cnpj(@Param("cnpj") Long cnpj);


}
