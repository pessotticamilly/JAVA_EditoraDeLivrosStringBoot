package br.senai.sc.EditoraDeLivros.repository;

import br.senai.sc.EditoraDeLivros.model.entities.Autor;
import br.senai.sc.EditoraDeLivros.model.entities.Livro;
import br.senai.sc.EditoraDeLivros.model.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByStatus(Status status);

    List<Livro> findByAutores(Autor autor);
}
