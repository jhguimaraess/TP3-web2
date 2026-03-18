package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
