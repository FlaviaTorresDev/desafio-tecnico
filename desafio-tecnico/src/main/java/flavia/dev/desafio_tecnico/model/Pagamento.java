package flavia.dev.desafio_tecnico.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigoDebito;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;


    private String numeroCartao;

    private BigDecimal valor;
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;


}

