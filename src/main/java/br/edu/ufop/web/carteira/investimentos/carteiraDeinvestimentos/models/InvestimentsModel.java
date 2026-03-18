package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "tb_investments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestimentsModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private EnumInvestimentsType type;
    private String symbol ;
    private Integer quantity;
    private Float purchasePrice;
    private LocalDateTime purchaseDate;
    private Float initialInvestment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private Float salePrice;
    private String statusProfitOrLoss;
    private Float finalInvestment;
    private Float profit;



    @PrePersist
    public void prePersist() {
            this.purchaseDate = LocalDateTime.now();
    }

}
