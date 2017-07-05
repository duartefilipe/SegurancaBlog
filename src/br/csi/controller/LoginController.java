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


@Controller
public class LoginController {

	int cont = 0;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	
	@Autowired
	private PostDao postDao;
	
	//chama a pagina principal INDEX
	@RequestMapping("index")
	public String redirecionaIndex(HttpServletRequest request, HttpSession session){
		session.isNew();
		java.util.Collection<Posts> listaPosts = postDao.listarPosts();
		request.setAttribute("ListaDePostsIndex", listaPosts);
		return "index";
                
	}
	
	@RequestMapping("login")
	public String login(String login, byte[] senha, HttpServletRequest request, HttpSession session) throws NoSuchAlgorithmException {
		
		MessageDigest hash = MessageDigest.getInstance("SHA-1");
		byte[] senhaHash = hash.digest(senha);
		
		if(login != null && senha!=null){
			
			Usuario usuario = usuarioDao.getByLoginAndSenha(login);
			if(usuario!=null){
				if(Arrays.equals(senhaHash , usuario.getSenha())){
					if(usuario.getTipo() == true && usuario.getAtivo().equals("sim")) {
						cont = 1;
						session.invalidate();
						java.util.Collection<Usuario> listaUsuario = usuarioDao.listaUser();
						java.util.Collection<Posts> listaPosts = postDao.listarPosts();
						request.setAttribute("ListaDePosts", listaPosts);
						request.setAttribute("ListaUsuario", listaUsuario);
						request.getSession().setAttribute("logado", usuario);
						return "bemVindoAdmin";
					}else if(usuario.getAtivo().equals("sim")){
						java.util.Collection<Posts> listaPosts = postDao.listarPostByUser(usuario);
						request.setAttribute("listaPost", listaPosts);
						request.getSession().setAttribute("logado", usuario);
						return "bemVindoComum";
					}
				}
				
				
				if(cont >= 3){
				   Usuario usuarioRetorno = usuarioDao.getByLoginAndSenha(login);
				   usuarioRetorno.setAtivo("nao");
				   usuarioDao.alteraUsuario(usuarioRetorno);
                                   request.setAttribute("msgdesativado", "Este usuário foi desativado por errar a senha mais de 3 vezes");
                                    System.out.println("tentou mais que 3 vezes");
				}
			}
			cont= cont+1;
			java.util.Collection<Posts> listaPosts = postDao.listarPosts();
			request.setAttribute("ListaDePostsIndex", listaPosts);
			return "index";
		}
		return "index";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		java.util.Collection<Posts> listaPosts = postDao.listarPosts();
		request.setAttribute("ListaDePostsIndex", listaPosts);
		request.getSession().invalidate();
		return "index";
	}
}
