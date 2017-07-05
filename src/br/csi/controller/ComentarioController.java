package br.csi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.csi.dao.PostDao;

import br.csi.modelo.Posts;
import br.csi.dao.ComentarioDao;
import br.csi.modelo.Comentario;

@Controller
public class ComentarioController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private ComentarioDao comentarioDao;

    @Transactional
    @RequestMapping("cadastraComentario")
    public String cadastraComentario(Long id_posts, HttpServletRequest request) {

        Comentario c = new Comentario();
        String Comentario = request.getParameter("textocoment");
        Posts post;
        post = postDao.getPostsId(id_posts);

        c.setId_posts(post);
        c.setTextocoment(Comentario);
        comentarioDao.criaComentario(c);
        post.getComentarios().add(c);
        request.setAttribute("Post", post);
        return "Post";
    }

}
