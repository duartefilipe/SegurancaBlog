package br.csi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.csi.dao.ComentarioDao;
import br.csi.dao.PostDao;
import br.csi.modelo.Comentario;
import br.csi.modelo.Posts;

@Controller
public class ComentarioController {

	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private ComentarioDao comentarioDao;
	
	@Transactional
	@RequestMapping("cadastraComentario")
	public String cadastraComentario(Long id_posts, HttpServletRequest request){
		
		Comentario coment = new Comentario();
		String textoComentario = request.getParameter("textocoment");
		System.out.println("AQUI NO CADASTRAR COMENTARIO// id posts-----"+id_posts);
		Posts post;
		post = postDao.getPostsId(id_posts);
	
		coment.setId_posts(post);
		coment.setTextocoment(textoComentario);
		comentarioDao.criaComentario(coment);
		post.getComentarios().add(coment);
		request.setAttribute("Post", post);
		return "umPost";
	}
	
}
