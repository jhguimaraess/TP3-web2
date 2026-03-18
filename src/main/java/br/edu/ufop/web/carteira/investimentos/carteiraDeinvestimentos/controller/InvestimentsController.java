package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.controller;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.*;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.InvestimentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
@AllArgsConstructor
public class InvestimentsController {

    private final InvestimentsService investimentsService;

    /**
     * Endpoint para obter o resumo dos investimentos.
     * Retorna um objeto InvestimentsSummaryDTO contendo o total investido, a contagem de ativos e o total por tipo.
     *
     * @return ResponseEntity com o resumo dos investimentos ou erro 400 em caso de falha.
     */
    @GetMapping("/summary")
    public ResponseEntity<InvestimentsSummaryDTO> investimentsSummary() {
        try {
            return ResponseEntity.ok(investimentsService.investimentsSummary());
        } catch (Exception e) {
            System.out.println("Erro ao exibir resumo dos investimentos: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para criar um novo investimento.
     * Recebe um objeto CreateInvestimentsDTO no corpo da requisição e retorna o investimento criado.
     *
     * @param investiment Objeto DTO contendo os dados do investimento a ser criado.
     * @return ResponseEntity com o InvestimentsDTO criado ou erro 400 em caso de falha.
     */
    @PostMapping
    public ResponseEntity<InvestimentsDTO> createInvestiment(@RequestBody CreateInvestimentsDTO investiment,
                                                             JwtAuthenticationToken token) {

            InvestimentsDTO createdInvestiment = investimentsService.createInvestiment(investiment, token);

            return ResponseEntity.ok(createdInvestiment);

    }

    /**
     * Endpoint para obter todos os investimentos.
     * Retorna uma lista de InvestimentsDTO representando todos os investimentos cadastrados.
     *
     * @return ResponseEntity com a lista de investimentos ou erro 400 em caso de falha.
     */
    @GetMapping
    public ResponseEntity<List<InvestimentsDTO>> getAllInvestments() {
        try {
            return ResponseEntity.ok(investimentsService.getAllInvestments());
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os investimentos: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Endpoint para obter investimentos por ID.
     * Retorna um InvestimentsDTO correspondente ao ID fornecido.
     *
     * @param  "id UUID do investimento a ser buscado.
     * @return ResponseEntity com o InvestimentsDTO encontrado ou erro 400 em caso de falha.
     */
    @GetMapping("/type={type}")
    public ResponseEntity<List<InvestimentsDTO>> getAllByTypeInvestiments(@PathVariable EnumInvestimentsType type) {
        try {
            return ResponseEntity.ok(investimentsService.getAllByTypeInvestiments(type));
        } catch (Exception e) {
            System.out.println("Erro ao buscar investimentos por tipo: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para deletar um investimento por ID.
     * Recebe o ID do investimento a ser deletado e retorna o InvestimentsDTO deletado.
     *
     * @param id UUID do investimento a ser deletado.
     * @return ResponseEntity com o InvestimentsDTO deletado ou erro 400 em caso de falha.
     */
    @DeleteMapping("/id={id}")
    public ResponseEntity<InvestimentsDTO> deleteInvestimentById(@PathVariable UUID id, JwtAuthenticationToken token) {
        try {
            return ResponseEntity.ok(investimentsService.deleteInvestimentById(id, token));
        } catch (Exception e) {
            System.out.println("Erro ao deletar investimento: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint para atualizar um investimento por ID.
     * Recebe um objeto EditInvestimentsDTO no corpo da requisição e retorna o investimento atualizado.
     *
     * @param investiment Objeto DTO contendo os dados do investimento a ser atualizado.
     * @return ResponseEntity com o EditInvestimentsDTO atualizado ou erro 400 em caso de falha.
     */
    @PutMapping
    public ResponseEntity<EditInvestimentsDTO> updateInvestimentById(@RequestBody EditInvestimentsDTO investiment) {
            return ResponseEntity.ok(investimentsService.updateInvestimentById(investiment));
    }


    @PutMapping("/sale")
    public ResponseEntity<InvestimentsDTO> updateStatusInvestimentById(@RequestBody SaleInvestimentsDTO saleInvestimentsDTO ) {

            return ResponseEntity.ok(investimentsService.CalculateProfitOrLoss(saleInvestimentsDTO));

    }




}
