package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.model.Usuario;

public class VerEnviadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		request.getSession().setAttribute("mailList", user.getEnviados());

		request.getSession().setAttribute("tittle", "Sent Mail");

		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
