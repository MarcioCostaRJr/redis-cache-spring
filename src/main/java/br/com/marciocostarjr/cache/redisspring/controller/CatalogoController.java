package br.com.marciocostarjr.cache.redisspring.controller;

import br.com.marciocostarjr.cache.redisspring.dto.CatalogoDTO;
import br.com.marciocostarjr.cache.redisspring.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService service;


    @GetMapping
    public ResponseEntity<Collection<CatalogoDTO>> obterTodos(){
        final var catalogos = service.obterTodos();
        return catalogos.isEmpty() ? noContent().build() : ok().body(catalogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoDTO> obterPorId(@PathVariable final Long id) {
        final var catalogo = service.obterPorId(id);
        return isNull(catalogo) ? noContent().build() : ok().body(catalogo);
    }

    @PostMapping
    public ResponseEntity<CatalogoDTO> criarNovo(@RequestBody final CatalogoDTO dto) {
        final var catalogo = service.salvar(dto);
        return isNull(catalogo) ? noContent().build() : status(CREATED).body(catalogo);
    }

    @PutMapping
    public ResponseEntity<CatalogoDTO> atualizarCatalogo(@RequestBody final CatalogoDTO dto) {
        final var catalogo = service.atualizar(dto);
        return isNull(catalogo) ? noContent().build() : ok().body(catalogo);
    }

    @DeleteMapping
    public ResponseEntity<Object> removerCatalogo(@PathVariable final Long id) {
        try {
            service.removerPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/lote")
    public ResponseEntity<Object> salvaLote(){
        return service.criarMassaEmLote() ? ok().build() : badRequest().build();
    }

}
