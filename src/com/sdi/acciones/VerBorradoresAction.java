package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.model.Usuario;

public class VerBorradoresAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		request.getSession().setAttribute("mailList", user.getBorradores());

		request.getSession().setAttribute("tittle", "Drafts");

		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
