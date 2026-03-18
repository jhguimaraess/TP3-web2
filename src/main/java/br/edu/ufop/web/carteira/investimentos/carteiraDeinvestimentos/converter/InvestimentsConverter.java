package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.converter;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.EditInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvestimentsConverter {


    public static InvestmentsDomain toInvestmentsDomain(CreateInvestimentsDTO investiments) {
        return InvestmentsDomain.builder()
                .type(investiments.type())
                .symbol(investiments.symbol())
                .quantity(investiments.quantity())
                .purchasePrice(investiments.purchasePrice())
                .build();
    }



    public static InvestimentsModel toInvestimentsModel(InvestmentsDomain investments) {
        return InvestimentsModel.builder()
                .id(investments.getId())
                .type(investments.getType())
                .symbol(investments.getSymbol())
                .quantity(investments.getQuantity())
                .purchasePrice(investments.getPurchasePrice())
                .purchaseDate(investments.getPurchaseDate())
                .initialInvestment(investments.getInitialInvestment())
                .build();
    }



    public static InvestimentsDTO toInvestimentsDTO(InvestimentsModel investments) {
        return new InvestimentsDTO(
                investments.getId(),
                investments.getType(),
                investments.getSymbol(),
                investments.getQuantity(),
                investments.getPurchasePrice(),
                investments.getPurchaseDate(),
                investments.getInitialInvestment(),
                investments.getUser().getUsername(),
                investments.getSalePrice(),
                investments.getStatusProfitOrLoss(),
                investments.getFinalInvestment(),
                investments.getProfit()

        );
    }



    public static InvestmentsDomain toEditInvestmentsDomain(EditInvestimentsDTO investments) {
        InvestmentsDomain investmentsDomain = new InvestmentsDomain();
        investmentsDomain.setId(investments.id());
        investmentsDomain.setQuantity(investments.quantity());
        investmentsDomain.setPurchasePrice(investments.purchasePrice());
        return investmentsDomain;
    }

    public static EditInvestimentsDTO toEditInvestmentsModel(InvestimentsModel investments) {
        return new EditInvestimentsDTO(
                investments.getId(),
                investments.getQuantity(),
                investments.getPurchasePrice()
        );
    }

    public static InvestmentsDomain toInvestmentsDomain(InvestimentsModel investments) {
        return InvestmentsDomain.builder()
                .id(investments.getId())
                .type(investments.getType())
                .symbol(investments.getSymbol())
                .quantity(investments.getQuantity())
                .purchasePrice(investments.getPurchasePrice())
                .purchaseDate(investments.getPurchaseDate())
                .initialInvestment(investments.getInitialInvestment())
                .salePrice(investments.getSalePrice())
                .statusProfitOrLoss(investments.getStatusProfitOrLoss())
                .finalInvestment(investments.getFinalInvestment())
                .build();
    }




}
