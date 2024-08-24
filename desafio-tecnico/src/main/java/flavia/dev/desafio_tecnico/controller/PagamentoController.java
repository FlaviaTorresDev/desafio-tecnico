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

    @GetMapping
    public List<Pagamento> listarPagamentos(@RequestParam(required = false) Integer codigoDebito,
                                            @RequestParam(required = false) String cpfCnpj,
                                            @RequestParam(required = false) StatusPagamento status) {
        return pagamentoService.listarPagamentos(codigoDebito, cpfCnpj, status);
    }

    @PutMapping("/atualizarStatus/{id}")
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusPagamento novoStatus) {
        Pagamento pagamentoAtualizado = pagamentoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/excluirPagamento/{id}")
    public ResponseEntity<Void> excluirPagamento(@PathVariable Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
