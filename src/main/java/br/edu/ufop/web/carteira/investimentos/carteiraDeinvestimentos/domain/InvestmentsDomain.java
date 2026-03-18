package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvestmentsDomain {
    private UUID id;
    private EnumInvestimentsType type;
    private String symbol ;
    private Integer quantity;
    private Float purchasePrice;
    private LocalDateTime purchaseDate;
    private Float initialInvestment;
    private User user;


    private Float salePrice;
    private String statusProfitOrLoss;
    private Float finalInvestment;
    private Float profit;





}
