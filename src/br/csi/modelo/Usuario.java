package br.csi.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
	
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    
	@Column (name = "NOME")
    private String nome;
    
    @Column (name = "LOGIN" , unique=true)
    private String login;
    
    @Column (name = "SENHA")
    private byte[] senha;
   
    @Column (name = "TIPO")
    private Boolean tipo;
    
    @Column(name = "ATIVO")
    private String ativo;
    
	
public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

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

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public byte[] getSenha() {
	return senha;
}

public void setSenha(byte[] senha) {
	this.senha = senha;
}

public Boolean getTipo() {
	return tipo;
}

public void setTipo(Boolean tipo) {
	this.tipo = tipo;
}

}
