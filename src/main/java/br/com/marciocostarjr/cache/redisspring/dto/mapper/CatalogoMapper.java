package br.com.marciocostarjr.cache.redisspring.dto.mapper;

import br.com.marciocostarjr.cache.redisspring.dto.CatalogoDTO;
import br.com.marciocostarjr.cache.redisspring.model.Catalogo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {

    Catalogo catalogoDTOparaEntity(final CatalogoDTO dto);

    CatalogoDTO catalogoEntityparaDTO(final Catalogo catalogo);
}
