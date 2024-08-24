package flavia.dev.desafio_tecnico.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O código do débito é obrigatório.")
    private int codigoDebito;

    @NotNull(message = "O CPF ou CNPJ é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @NotNull(message = "O método de pagamento é obrigatório.")
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    

    private String numeroCartao;
    
    @NotNull(message = "O valor do pagamento é obrigatório.")
    @Positive(message = "O valor do pagamento deve ser positivo.")
    private BigDecimal valor;
    
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    
   


}

