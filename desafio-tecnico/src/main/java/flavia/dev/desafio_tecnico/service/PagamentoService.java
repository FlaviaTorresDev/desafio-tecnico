package flavia.dev.desafio_tecnico.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.desafio_tecnico.model.MetodoPagamento;
import flavia.dev.desafio_tecnico.model.Pagamento;
import flavia.dev.desafio_tecnico.model.StatusPagamento;
import flavia.dev.desafio_tecnico.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento criarPagamento(Pagamento pagamento) {
        validarNumeroCartao(pagamento);
        pagamento.setStatus(StatusPagamento.PENDENTE);
        return pagamentoRepository.save(pagamento);
    }

    private void validarNumeroCartao(Pagamento pagamento) {
        if ((pagamento.getMetodoPagamento() == MetodoPagamento.CARTAO_CREDITO 
                || pagamento.getMetodoPagamento() == MetodoPagamento.CARTAO_DEBITO)) {
            if (pagamento.getNumeroCartao() == null || pagamento.getNumeroCartao().isEmpty()) {
                throw new IllegalArgumentException("O número do cartão é obrigatório para pagamentos com cartão de crédito ou débito.");
            }
        } else if (pagamento.getNumeroCartao() != null) {
            throw new IllegalArgumentException("O número do cartão não deve ser preenchido para métodos de pagamento diferentes de cartão de crédito ou débito.");
        }
    }


    
    public List<Pagamento> listarPorCodigoDebito(Integer codigoDebito) {
        return pagamentoRepository.findByCodigoDebito(codigoDebito);
    }

    public List<Pagamento> listarPorCpfCnpj(String cpfCnpj) {
        return pagamentoRepository.findByCpfCnpj(cpfCnpj);
    }

    public List<Pagamento> listarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findByStatus(status);
    }
    
    
    public Pagamento atualizarStatus(Long id, StatusPagamento novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Pagamento não encontrado"));
        
        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_SUCESSO) {
            throw new IllegalStateException("Pagamento já processado com sucesso, não pode ser alterado.");
        }
        
        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_FALHA && novoStatus != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Pagamento processado com falha só pode voltar para pendente.");
        }

        pagamento.setStatus(novoStatus);
        return pagamentoRepository.save(pagamento);
    }

    public void excluirPagamento(Long codigoDebito) {
        Pagamento pagamento = pagamentoRepository.findById(codigoDebito)
        		.orElseThrow(() -> new NoSuchElementException("Pagamento não encontrado"));

        
        if (pagamento.getStatus() == StatusPagamento.PENDENTE) {
            pagamento.setAtivo(false);
            pagamentoRepository.save(pagamento);
        } else {
            throw new IllegalStateException("Somente pagamentos pendentes podem ser excluídos.");
        }
    }
}
