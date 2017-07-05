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

        System.out.println("Nome Usuario input-----" + usuario.getNome());
        System.out.println("Login Usuario input-----" + usuario.getLogin());
        System.out.println("Senha Usuario input-----" + usuario.getSenha());
        System.out.println("Tipo Usuario input-------" + tipoo);

        if (tipoo.equals("1")) {
            usuario.setTipo(true);
            usuario.setAtivo(ativo);
        } else {
            usuario.setTipo(false);
            usuario.setAtivo(ativo);
        }

        usuarioDao.cadastraUser(usuario);
        //System.out.println("RETORNO NO CONTROLLER USUARIO-----"+usuario.getNome());
        java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
        java.util.Collection<Posts> listaPosts = postDao.listarPosts();

        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        return "bemVindoAdmin";
    }

    @RequestMapping("deletaUsuario")
    public String deletaUser(Long id, HttpServletRequest request) {
        try {
            Usuario user = usuarioDao.getUsuario(id);
            usuarioDao.deletaUser(user);
            request.setAttribute("msgSucesso", "Usuário deletado com sucesso");
        } catch (Exception e) {
            System.out.println("ERRO AO deletar Usuário");
            request.setAttribute("msgErro", "Este usuário contém um POST");
        }
        System.out.println("ERRO AO deletar Usuário");
        request.setAttribute("msgErro", "Este usuário contém um POST");
        java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
        java.util.Collection<Posts> listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        return "bemVindoAdmin";
    }

    @RequestMapping("situacaoUsuario")
    public String trocaAtivo(Long id, HttpServletRequest request, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("logado");

        Usuario usuarioRetorno = usuarioDao.getUsuario(id);
        if (usuarioRetorno.getAtivo().equals("sim")) {
            usuarioRetorno.setAtivo("nao");
            usuarioDao.alteraUsuario(usuarioRetorno);
        } else {
            usuarioRetorno.setAtivo("sim");
            usuarioDao.alteraUsuario(usuarioRetorno);
        }

        java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
        java.util.Collection<Posts> listaPosts = postDao.listarPosts();
        request.setAttribute("ListaDePosts", listaPosts);
        request.setAttribute("ListaUsuario", listaUsuario);
        request.getSession().setAttribute("logado", user);
        return "bemVindoAdmin";
    }

}
