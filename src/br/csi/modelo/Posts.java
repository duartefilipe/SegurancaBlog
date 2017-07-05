package br.csi.modelo;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POSTS")
public class Posts {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long ID;

    @NotNull(message = "Campo Obrigatório")
    @Size(min = 1, max = 255, message = "Insira o Título")
    @Column(name = "titulo")
    private String titulo;

    @NotNull(message = "Campo Obrigatório")
    @Size(min = 1, max = 1000, message = "Insira a Descrição")
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @NotNull(message = "Campo Obrigatório")
    @Size(min = 1, max = 10)
    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private Collection<Comentario> comentarios;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Collection<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario getusuario() {
        return usuario;
    }

    public void setId_usuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
