package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

public class VerUsuariosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Usuario> activeUserList;
		List<Usuario> inactiveUserList;

		activeUserList = Factories.persistence.createUsuarioDao()
				.getUsuariosActivos();

		inactiveUserList = Factories.persistence.createUsuarioDao()
				.getUsuariosInactivos();

		request.setAttribute("activeUserList", activeUserList);

		request.setAttribute("inactiveUserList", inactiveUserList);

		request.setAttribute("tittle", "Users");

		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
