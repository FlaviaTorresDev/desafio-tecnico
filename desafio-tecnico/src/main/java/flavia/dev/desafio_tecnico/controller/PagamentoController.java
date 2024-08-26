package flavia.dev.desafio_tecnico.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flavia.dev.desafio_tecnico.model.Pagamento;
import flavia.dev.desafio_tecnico.model.StatusPagamento;
import flavia.dev.desafio_tecnico.service.PagamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/salvar")
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody @Valid Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoService.criarPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/codigo-debito/{codigoDebito}")
    public ResponseEntity<List<Pagamento>> listarPorCodigoDebito(@PathVariable Integer codigoDebito) {
        List<Pagamento> pagamentos = pagamentoService.listarPorCodigoDebito(codigoDebito);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<List<Pagamento>> listarPorCpfCnpj(@PathVariable String cpfCnpj) {
        List<Pagamento> pagamentos = pagamentoService.listarPorCpfCnpj(cpfCnpj);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pagamento>> listarPorStatus(@PathVariable StatusPagamento status) {
        List<Pagamento> pagamentos = pagamentoService.listarPorStatus(status);
        return ResponseEntity.ok(pagamentos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusPagamento novoStatus) {
        Pagamento pagamentoAtualizado = pagamentoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/excluirPagamento/{codigoDebito}")
    public ResponseEntity<Void> excluirPagamento(@PathVariable Long codigoDebito) {
        pagamentoService.excluirPagamento(codigoDebito);
        return ResponseEntity.noContent().build();
    }
}
