package com.app.barbeariashop.service;

import com.app.barbeariashop.model.Despesa;
import com.app.barbeariashop.model.Pagamento;
import com.app.barbeariashop.repository.AgendamentoRepository;
import com.app.barbeariashop.repository.DespesaRepository;
import com.app.barbeariashop.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final PagamentoRepository pagamentoRepository;
    private final DespesaRepository despesaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public BigDecimal calcularLucroTotal() {
        BigDecimal totalReceita = pagamentoRepository.findAll().stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesa = despesaRepository.findAll().stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalReceita.subtract(totalDespesa);
    }
}
