package br.senai.sc.EditoraDeLivros.repository;

import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
