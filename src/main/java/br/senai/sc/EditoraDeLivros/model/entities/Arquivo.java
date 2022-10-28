package br.senai.sc.EditoraDeLivros.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_arquivo")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NonNull
    private String nome;
    @NonNull
    private String tipo;
    @Lob
    @NonNull
    private byte[] dados;
}
