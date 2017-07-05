package br.csi.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.csi.modelo.Usuario;

public class AutenticacaoInterceptor extends HandlerInterceptorAdapter{

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception{
		
		Usuario u = (Usuario) request.getSession().getAttribute("logado");
		
		if (u == null && !request.getRequestURI().endsWith("index")  && !request.getRequestURI().endsWith("index")  && !request.getRequestURI().endsWith("index")){
			response.sendRedirect("index");
			return false;
		}
		return true;
		
	}
	
}
