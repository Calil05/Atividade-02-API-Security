package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Produto;
import application.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public Iterable<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto getOne(@PathVariable long id) {
        Optional<Produto> resultado = produtoRepository.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
            );
        }
        return resultado.get();
    }

    @PostMapping
    public Produto post(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto put(@PathVariable long id, @RequestBody Produto novosDados){
        Optional<Produto> resultado = produtoRepository.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
            );
        }

        resultado.get().setNome(novosDados.getNome());
        resultado.get().setDescricao(novosDados.getDescricao());
        resultado.get().setValor(novosDados.getValor());

        return produtoRepository.save(resultado.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
            );
        }

        produtoRepository.deleteById(id);
    }
    
}
