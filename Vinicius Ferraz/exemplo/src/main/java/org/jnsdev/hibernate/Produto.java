package org.jnsdev.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Autor Jairo Nascimento
 * @Created 11/04/2023 - 13:59
 */

@Entity
public class Produto {

    @Id
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
