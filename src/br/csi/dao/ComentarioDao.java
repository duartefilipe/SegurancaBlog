package br.csi.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.csi.modelo.Comentario;

@Repository
public class ComentarioDao {

	
	   @Autowired
	   private SessionFactory sessionFactory;
	   

	   @Transactional
	   public void criaComentario(Comentario comment) {
	       sessionFactory.getCurrentSession().save(comment);
	   }
	   
}
