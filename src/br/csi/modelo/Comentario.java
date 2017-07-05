package br.csi.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    @Column(name = "ID_COMENT")
    private Long idcoment;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEXTOCOMENT", length = 1000)
    private String textocoment;

    @ManyToOne
    @JoinColumn(name = "ID_POSTS")
    private Posts posts;

    public Long getIdcoment() {
        return idcoment;
    }

    public void setIdcoment(Long idcoment) {
        this.idcoment = idcoment;
    }

    public String getTextocoment() {
        return textocoment;
    }

    public void setTextocoment(String textocoment) {
        this.textocoment = textocoment;
    }

    public Posts getId_posts() {
        return posts;
    }

    public void setId_posts(Posts id_posts) {
        this.posts = id_posts;
    }

}
