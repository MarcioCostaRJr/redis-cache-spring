package br.com.marciocostarjr.cache.redisspring.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Representa de forma minimalista um catálogo de filmes, séries ou algo do gênero")
public class CatalogoDTO implements Serializable {

    @ApiModelProperty(value = "1", notes = "Identificador do Catálogo")
    private Long id;

    @ApiModelProperty(value = "Filme YYY", notes = "Título do Catálogo")
    private String titulo;

    @ApiModelProperty(value = "Filme YYY se baseia em fatos reais", notes = "Descrição do Catálogo")
    private String descricaoCatalogo;

    @ApiModelProperty(value = "2022-01-01", notes = "Data de Lançamento do Catálogo")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataLancamento;

    @ApiModelProperty(value = "120", notes = "Duração em minutos do Catálogo")
    private Integer duracao;

    @ApiModelProperty(value = "Ação/Terror/Suspense", notes = "Gênero do Catálogo")
    private String genero;

    @ApiModelProperty(value = "true/false", notes = "Flag de ativação do Catálogo")
    private boolean ativo;

}
