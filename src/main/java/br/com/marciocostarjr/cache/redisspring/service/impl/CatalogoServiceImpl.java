package br.com.marciocostarjr.cache.redisspring.service.impl;

import br.com.marciocostarjr.cache.redisspring.dto.CatalogoDTO;
import br.com.marciocostarjr.cache.redisspring.dto.mapper.CatalogoMapper;
import br.com.marciocostarjr.cache.redisspring.model.Catalogo;
import br.com.marciocostarjr.cache.redisspring.repository.CatalogoRepository;
import br.com.marciocostarjr.cache.redisspring.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.valueOf;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "catalagoCache")
public class CatalogoServiceImpl implements CatalogoService {

    private static final String TITULO = "Filme de Teste ";
    private static final String DESCRICAO = "A descrição deve conter mais informações referentes ao titulo ";
    private static final String GENERO = "aleatório... =]";

    private final CatalogoRepository repository;

    private final CatalogoMapper mapperDTO;

    @Cacheable(cacheNames = "catalogos")
    @Override
    public Collection<CatalogoDTO> obterTodos() {
        final var catalogos = repository.findAll();
        return catalogos.stream().map(mapperDTO::catalogoEntityparaDTO).toList();
    }

    @CacheEvict(cacheNames = "catalogos", allEntries = true)
    @Override
    public CatalogoDTO salvar(final CatalogoDTO catalogo) {
        final var catalogoSalvo = repository.save(mapperDTO.catalogoDTOparaEntity(catalogo));
        return mapperDTO.catalogoEntityparaDTO(catalogoSalvo);
    }

    @CacheEvict(cacheNames = "catalogos", allEntries = true)
    @Override
    public CatalogoDTO atualizar(final CatalogoDTO catalogo) {
        final var catalogoOptional = repository.findById(catalogo.getId());
        catalogoOptional.ifPresent(catalogoBase -> {
            catalogoBase.setTitulo(catalogo.getTitulo());
            catalogoBase.setDescricaoCatalogo(catalogo.getDescricaoCatalogo());
            catalogoBase.setDataLancamento(catalogo.getDataLancamento());
            catalogoBase.setDuracao(catalogo.getDuracao());
            catalogoBase.setGenero(catalogo.getGenero());
            catalogoBase.setAtivo(catalogo.isAtivo());
            repository.save(catalogoBase);
        });
        return catalogoOptional.map(mapperDTO::catalogoEntityparaDTO).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "catalogo", key = "#id"),
            @CacheEvict(cacheNames = "catalogos", allEntries = true)})
    @Override
    public void removerPorId(final Long id) {
        repository.deleteById(id);
    }

    @Cacheable(cacheNames = "catalogo", key = "#id", unless = "#result == null")
    @Override
    public CatalogoDTO obterPorId(final Long id) {
        return repository.findById(id).map(mapperDTO::catalogoEntityparaDTO).orElse(null);
    }

    public boolean criarMassaEmLote() {
        Collection<Catalogo> catalogos = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            final String indice = valueOf(i);
            catalogos.add(Catalogo.builder()
                    .titulo(TITULO.concat(indice))
                    .descricaoCatalogo(DESCRICAO.concat(indice))
                    .dataLancamento(now())
                    .duracao((i * 2) + 1)
                    .genero(GENERO)
                    .ativo(TRUE)
                    .build());
        }
        try {
            repository.saveAll(catalogos);
            return TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return FALSE;
        }
    }
}
