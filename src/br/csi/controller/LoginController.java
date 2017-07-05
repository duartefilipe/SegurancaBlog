package br.csi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.csi.dao.PostDao;
import br.csi.dao.UsuarioDao;
import br.csi.modelo.Posts;
import br.csi.modelo.Usuario;
import java.util.Collection;

@Controller
public class LoginController {

    int cont = 0;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PostDao postDao;

    @RequestMapping("index")
    public String redirecionaIndex(HttpServletRequest request, HttpSession session) {
        session.isNew();
        Collection<Posts> listaPosts;
        listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePostsIndex", listaPosts);
        return "index";
    }

    @RequestMapping("login")
    public String login(String login, byte[] senha, HttpServletRequest request, HttpSession session) throws NoSuchAlgorithmException {

        MessageDigest hash = MessageDigest.getInstance("SHA-1");
        byte[] senhaHash = hash.digest(senha);

        if (login != null && senha != null) {

            Usuario u = usuarioDao.getByLoginAndSenha(login);
            if (u != null) {
                if (Arrays.equals(senhaHash, u.getSenha())) {
                    if (u.getTipo() == true && u.getAtivo().equals("sim")) {
                        cont = 0;
                        session.invalidate();
                        Collection<Usuario> listaUsuario;
                        listaUsuario = usuarioDao.listaUser();
                        Collection<Posts> listaPosts;
                        listaPosts = postDao.listarPosts();
                        request.setAttribute("ListaDePosts", listaPosts);
                        request.setAttribute("ListaUsuario", listaUsuario);
                        request.getSession().setAttribute("logado", u);
                        return "Admin";
                    } else if (u.getAtivo().equals("sim")) {
                        Collection<Posts> listaPosts;
                        listaPosts = postDao.listarPostByUser(u);
                        request.setAttribute("listaPost", listaPosts);
                        request.getSession().setAttribute("logado", u);
                        return "Usuario";
                    }
                }

                if (cont >= 3) {
                    Usuario usuarioRetorno = usuarioDao.getByLoginAndSenha(login);
                    usuarioRetorno.setAtivo("nao");
                    usuarioDao.alteraUsuario(usuarioRetorno);
                }
            }
            cont += 1;
            Collection<Posts> listaPosts;
            listaPosts = postDao.listarPosts();
            request.setAttribute("ListaDePostsIndex", listaPosts);
            return "index";
        }
        return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        Collection<Posts> listaPosts;
        listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePostsIndex", listaPosts);
        request.getSession().invalidate();
        return "index";
    }
}
