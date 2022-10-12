package br.senai.sc.EditoraDeLivros.model.service;

import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import br.senai.sc.EditoraDeLivros.repository.PessoaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PessoaService {
    private PessoaRepository pessoaRopository;

    public PessoaService(PessoaRepository pessoaDao) {
        this.pessoaRopository = pessoaDao;
    }

    public List<Pessoa> findAll() {
        return pessoaRopository.findAll();
    }

    public <S extends Pessoa> S save(S entity) {
        return pessoaRopository.save(entity);
    }

    public Optional<Pessoa> findById(Long aLong) {
        return pessoaRopository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        pessoaRopository.deleteById(aLong);
    }

    public Optional<Pessoa> findByEmail(String email) {
        return pessoaRopository.findByEmail(email);
    }

    public boolean existByEmail(String email) {
        return pessoaRopository.existByEmail(email);
    }

    public boolean existById(Long cpf) {
        return pessoaRopository.existById(cpf);
    }
}