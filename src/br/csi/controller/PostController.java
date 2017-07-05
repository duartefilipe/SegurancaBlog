package br.csi.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.csi.dao.PostDao;
import br.csi.dao.UsuarioDao;
import br.csi.modelo.Posts;
import br.csi.modelo.Usuario;


@Controller
public class PostController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PostDao postDao;

    @RequestMapping("cadastraPostAdmin")
    public String cadastraPost(Posts posts, HttpSession session, HttpServletRequest request) throws Exception {

        Usuario user = (Usuario) session.getAttribute("logado");

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        System.out.println(out.format(calendar.getTime()));
        posts.setData(out.format(calendar.getTime()));
        posts.setId_usuario(user);

        postDao.criaPost(posts);
        java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
        java.util.Collection<Posts> listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        request.setAttribute("msgSucesso", "Post Cadastrado com Sucesso");
        return "Admin";

    }

    @RequestMapping("cadastraPostComum")
    public String cadastraPostComum(Posts posts, HttpSession session, HttpServletRequest request) throws Exception {

        Usuario user = (Usuario) session.getAttribute("logado");

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        posts.setData(out.format(calendar.getTime()));
        posts.setId_usuario(user);

        postDao.criaPost(posts);
        request.setAttribute("msgSucesso", "Post Cadastrado com Sucesso");

        java.util.Collection<Posts> listaPosts = postDao.listarPostByUser(user);
        request.setAttribute("listaPost", listaPosts);

        return "Usuario";

    }

    @RequestMapping("RedirecionaUmPost")
    public String redirecionaUmPost(Long id, HttpServletRequest request) {
        Posts postCompleto = postDao.getPostsId(id);
        request.setAttribute("Post", postCompleto);
        return "Post";
    }

    @RequestMapping("deletaPostUsuarioComum")
    public String deletaPost(Long id, HttpSession session, HttpServletRequest request) {
        Usuario user = (Usuario) session.getAttribute("logado");

        Posts postRetorno = postDao.getPostsId(id);
        if (user.getId() == postRetorno.getUsuario().getId()) {
            Posts post = postDao.getPostsId(id);
            postDao.removePost(post);
            java.util.Collection<Posts> listaPosts = postDao.listarPostByUser(user);
            request.setAttribute("listaPost", listaPosts);
            return "Usuario";
        } else {
            session.invalidate();
            return "erro";
        }
    }

    @RequestMapping("deletaPostUsuarioAdmin")
    public String deletaPostAdmin(Long id, HttpSession session, HttpServletRequest request) {
        Usuario user = (Usuario) session.getAttribute("logado");

        Posts post = postDao.getPostsId(id);
        postDao.removePost(post);
        java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
        java.util.Collection<Posts> listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        return "Admin";

    }

    @RequestMapping("RedirecionaAlteraPost")
    public String redirecionaAlteraPost(Long id, HttpServletRequest request, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("logado");

        Posts postRetorno = postDao.getPostsId(id);
        if (user.getId() == postRetorno.getUsuario().getId()) {
            Posts postCompleto = postDao.getPostsId(id);
            request.setAttribute("PostAltera", postCompleto);
            return "alteraPost";
        } else {
            session.invalidate();
            return "erro";
        }
    }

    @RequestMapping("RedirecionaAlteraPostUsuarioAdmin")
    public String redirecionaAlteraPostUsuarioAdmin(Long id, HttpServletRequest request, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("logado"); 
        Posts postRetorno = postDao.getPostsId(id);
        Posts postCompleto = postDao.getPostsId(id);
        request.setAttribute("PostAltera", postCompleto);
        return "alteraPost";

    }

    @RequestMapping("alteraPostUsuarioComum")
    public String alteraPostUsuarioComum(Posts post, HttpSession session, HttpServletRequest request) {
        Usuario user = (Usuario) session.getAttribute("logado");

        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        post.setData(out.format(calendar.getTime()));
        post.setId_usuario(user);

        postDao.criaPost(post);

        if (user.getTipo() == false) {
            java.util.Collection<Posts> listaPosts = postDao.listarPostByUser(user);
            request.setAttribute("listaPost", listaPosts);
            return "Usuario";
        } else {
            java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
            java.util.Collection<Posts> listaPosts = postDao.listarPosts();
            request.setAttribute("ListaDePosts", listaPosts);
            request.setAttribute("ListaUsuario", listaUsuario);
            return "Admin";
        }
    }

    @RequestMapping("MostraTodosPost")
    public String mostraTodosPost(HttpServletRequest request) {

        java.util.Collection<Posts> listaPostsAntigos = postDao.postsAntigos();

        java.util.Collection<Posts> listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePostsIndex", listaPosts);
        request.setAttribute("PostsAntigos", listaPostsAntigos);
        return "index";
    }

}
