package com.sdi.acciones;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.check.Check;
import com.sdi.model.Usuario;
import com.sdi.persistence.exception.BusinessException;

public class ValidarseAction implements Accion {

	private Usuario user;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String login = request.getParameter("username");
		String password = request.getParameter("password");

		try {

			assertExisteUsuario(request, login);

			assertValidSession(request, password);

			request.getSession().setAttribute("user", user);

			return "EXITO";

		} catch (SQLException sqle) {
			return "FRACASO";
		} catch (BusinessException bex) {
			return "FRACASO";
		}

	}

	private void assertValidSession(HttpServletRequest request, String password)
			throws BusinessException {
		if (!Check.checkSession(user, password)) {
			Check.throwError(request, "The introduced password is not correct");
		}
	}

	private void assertExisteUsuario(HttpServletRequest request, String login)
			throws SQLException, BusinessException {
		Usuario user = Check.getUsuarioByLogin(login);
		if (user == null) {
			Check.throwError(request, "The introduced user does not exist");
		}
		this.user = user;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
