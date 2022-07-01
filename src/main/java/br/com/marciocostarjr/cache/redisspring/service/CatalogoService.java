package br.com.marciocostarjr.cache.redisspring.service;

import br.com.marciocostarjr.cache.redisspring.dto.CatalogoDTO;

import java.util.Collection;

public interface CatalogoService {

    Collection<CatalogoDTO> obterTodos();

    CatalogoDTO salvar(final CatalogoDTO catalogo);

    CatalogoDTO atualizar(final CatalogoDTO catalogo);

    void removerPorId(final Long id);

    CatalogoDTO obterPorId(final Long id);

    boolean criarMassaEmLote();
}