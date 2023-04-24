package penta.sisPenta.gestaoFinanceira.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import penta.sisPenta.gestaoFinanceira.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {





}
