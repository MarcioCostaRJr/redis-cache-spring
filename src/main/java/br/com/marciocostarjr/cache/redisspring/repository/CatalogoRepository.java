package br.com.marciocostarjr.cache.redisspring.repository;

import br.com.marciocostarjr.cache.redisspring.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
}
