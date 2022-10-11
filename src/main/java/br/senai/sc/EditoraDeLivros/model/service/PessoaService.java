package br.senai.sc.EditoraDeLivros.model.service;

import br.senai.sc.EditoraDeLivros.model.entities.Pessoa;
import br.senai.sc.EditoraDeLivros.repository.PessoaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    public <S extends Pessoa> Optional<S> findOne(Example<S> example) {
        return pessoaRopository.findOne(example);
    }

    public <S extends Pessoa> Page<S> findAll(Example<S> example, Pageable pageable) {
        return pessoaRopository.findAll(example, pageable);
    }

    public <S extends Pessoa> long count(Example<S> example) {
        return pessoaRopository.count(example);
    }

    public <S extends Pessoa> boolean exists(Example<S> example) {
        return pessoaRopository.exists(example);
    }

    public <S extends Pessoa, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return pessoaRopository.findBy(example, queryFunction);
    }
}