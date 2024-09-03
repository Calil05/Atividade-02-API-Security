package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    
} 