package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

public class ListarUsuariosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Usuario> usuarios;
		
		try {
			usuarios=Factories.persistence.createUsuarioDao().getUsuarios();
			request.setAttribute("listaUsuarios", usuarios);
			Log.debug("Obtenida lista de usuarios conteniendo [%d] usuarios", usuarios.size());
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de usuarios");
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
