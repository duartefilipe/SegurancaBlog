package br.csi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class UsuarioController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping("cadastraUsuarioAdmin")
    public String cadastraUsuario(Usuario usuario, String tipoo, HttpServletRequest request) throws NoSuchAlgorithmException {

        MessageDigest hash = MessageDigest.getInstance("SHA-1");
        byte[] senhahash = hash.digest(usuario.getSenha());
        usuario.setSenha(senhahash);
        String ativo = "sim";

        if (tipoo.equals("1")) {
            usuario.setTipo(true);
            usuario.setAtivo(ativo);
        } else {
            usuario.setTipo(false);
            usuario.setAtivo(ativo);
        }

        usuarioDao.cadastraUser(usuario);
        Collection <Usuario> listaUsuario;
        listaUsuario = usuarioDao.listaUser();
        Collection<Posts> listaPosts;
        listaPosts= postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        return "Admin";
    }

    @RequestMapping("deletaUsuario")
    public String deletaUser(Long id, HttpServletRequest request) {
        try {
            Usuario user = usuarioDao.getUsuario(id);
            usuarioDao.deletaUser(user);
            request.setAttribute("msgSucesso", "Usuário deletado com sucesso");
        } catch (Exception e) {
            request.setAttribute("msgErro", "Este usuário contém um POST");
        }
        request.setAttribute("msgErro", "Este usuário contém um POST");
        Collection<Usuario> listaUsuario;
        listaUsuario= usuarioDao.listaUser();
        Collection<Posts> listaPosts;
        listaPosts= postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        return "Admin";
    }

    @RequestMapping("situacaoUsuario")
    public String situacao(Long id, HttpServletRequest request, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("logado");

        Usuario usuarioRetorno = usuarioDao.getUsuario(id);
        if (usuarioRetorno.getAtivo().equals("sim")) {
            usuarioRetorno.setAtivo("nao");
            usuarioDao.alteraUsuario(usuarioRetorno);
        } else {
            usuarioRetorno.setAtivo("sim");
            usuarioDao.alteraUsuario(usuarioRetorno);
        }

        Collection<Usuario> listaUsuario;
        listaUsuario= usuarioDao.listaUser();
        Collection<Posts> listaPosts;
        listaPosts= postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        request.getSession().setAttribute("logado", user);
        return "Admin";
    }

}
