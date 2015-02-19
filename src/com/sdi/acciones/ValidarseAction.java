package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

public class ValidarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String resultado="EXITO";
		String nombreUsuario=request.getParameter("nombreUsuario");
		HttpSession session=request.getSession();
		if (session.getAttribute("user")==null) {
			Usuario usuario=Factories.persistence.createUsuarioDao().findByLogin(nombreUsuario);
			if (usuario!=null) {
				session.setAttribute("user", usuario);
				int contador=Integer.parseInt((String)request.getServletContext().getAttribute("contador"));
				request.getServletContext().setAttribute("contador", String.valueOf(contador+1));
				Log.info("El usuario [%s] ha iniciado sesión",nombreUsuario);
			}
			else {
				session.invalidate();
				Log.info("El usuario [%s] no está registrado",nombreUsuario);
				resultado="FRACASO";
			}
		}
		else
			if (!nombreUsuario.equals(session.getAttribute("user"))) {
				Log.info("Se ha intentado iniciar sesión como [%s] teniendo la sesión iniciada como [%s]",nombreUsuario,((Usuario)session.getAttribute("user")).getNombre());
				session.invalidate();
				resultado="FRACASO";
			}
		return resultado;
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
