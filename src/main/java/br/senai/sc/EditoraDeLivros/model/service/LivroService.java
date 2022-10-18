package br.senai.sc.EditoraDeLivros.model.service;

import br.senai.sc.EditoraDeLivros.model.entities.Livro;
import br.senai.sc.EditoraDeLivros.model.entities.Status;
import br.senai.sc.EditoraDeLivros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Optional<Livro> findById(Long isbn) {
        return livroRepository.findById(isbn);
    }

    public List<Livro> findByStatus(Status status) {
        return livroRepository.findByStatus(status);
    }

    public List<Livro> findAll(){
        return livroRepository.findAll();
    }

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deleteById(Long isbn) {
        livroRepository.deleteById(isbn);
    }

    public boolean existById(Long isbn) {
        return livroRepository.existsById(isbn);
    }
}