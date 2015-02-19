package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String nuevoEmail=request.getParameter("email");
		HttpSession session=request.getSession();
		Usuario usuario=((Usuario)session.getAttribute("user"));
		usuario.setEmail(nuevoEmail);
		try {
			Factories.persistence.createUsuarioDao().update(usuario);
			Log.debug("Modificado email de [%s] con el valor [%s]", usuario.getLogin(), nuevoEmail);
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando el email de [%s]", usuario.getLogin());
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
