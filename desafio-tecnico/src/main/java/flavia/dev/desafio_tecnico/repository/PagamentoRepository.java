package flavia.dev.desafio_tecnico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import flavia.dev.desafio_tecnico.model.Pagamento;
import flavia.dev.desafio_tecnico.model.StatusPagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
    List<Pagamento> findByCodigoDebito(Integer codigoDebito);
    
    List<Pagamento> findByCpfCnpj(String cpfCnpj);
    
    List<Pagamento> findByStatus(StatusPagamento status);
    
    List<Pagamento> findByAtivo(boolean ativo);
}
