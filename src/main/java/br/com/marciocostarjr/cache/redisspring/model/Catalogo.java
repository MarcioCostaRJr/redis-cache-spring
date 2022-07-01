package br.com.marciocostarjr.cache.redisspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "catalogo", schema = "public")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    private String titulo;
    private String descricaoCatalogo;
    private LocalDate dataLancamento;
    private Integer duracao;
    private String genero;
    private boolean ativo;

}
