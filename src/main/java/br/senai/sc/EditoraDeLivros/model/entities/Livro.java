package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_livro")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Livro {
    @Id
    @Column(length = 13, nullable = false, unique = true)
    private Long isbn;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer qntdPaginas;

    @Column
    private Double paginasRevisadas;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JoinColumn(name = "cpf_revisor")
    @ManyToOne
    private Revisor revisor;

    @ManyToMany
    @JoinTable(name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "isbn_livro", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cpf_autor", nullable = false))
    private List<Autor> autores;

    @JoinColumn(name = "cnpj_editora")
    @ManyToOne
    private Editora editora;

    @OneToOne(cascade = CascadeType.ALL)
    private Arquivo arquivo;

    public void setArquivo(MultipartFile file) {
       try{
           this.arquivo = new Arquivo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
       } catch(Exception e) {
           throw new RuntimeException(e);
       }
    }
}