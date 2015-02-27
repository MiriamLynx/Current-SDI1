package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class PerfilAction implements Accion {

	private String option;

	public PerfilAction(String option) {
		this.option = option;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String login = request.getParameter("user");

		UsuarioDao usersDao = Factories.persistence.createUsuarioDao();

		Usuario user = usersDao.findByLogin(login);

		if (this.option.equals("profile")) {
			request.setAttribute("currentpassword", true);
			request.setAttribute("profile", (Usuario) request.getSession()
					.getAttribute("user"));
		} else {
			request.setAttribute("profile", user);
		}

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
