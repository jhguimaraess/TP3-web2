package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories;


import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IInvestimentsRepository extends JpaRepository<InvestimentsModel, UUID> {

 List<InvestimentsModel> findAllByType(EnumInvestimentsType type);

 List<InvestimentsModel> findByTypeAndUser(EnumInvestimentsType type, User userId);

 @Query("SELECT SUM(i.initialInvestment) FROM InvestimentsModel i")
 Float sumAllInitialInvestments();

 @Query("SELECT COUNT(i) FROM InvestimentsModel i")
 Long countAllInvestments();


 @Query("SELECT i.type, SUM(i.initialInvestment) FROM InvestimentsModel i GROUP BY i.type")
 List<Object[]> sumInitialInvestmentByType();


 List<InvestimentsModel> findByUser(User user);
}
