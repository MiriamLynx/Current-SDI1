package com.sdi.acciones;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.check.Check;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Correo;
import com.sdi.model.Usuario;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.BusinessException;

public class ValidarseAction implements Accion {

	private Usuario user;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Correo> mailList;
		List<Usuario> activeUserList;
		List<Usuario> inactiveUserList;

		String login = request.getParameter("username");
		String password = request.getParameter("password");

		UsuarioDao usersDao = Factories.persistence.createUsuarioDao();
		CorreoDao mailsDao = Factories.persistence.createCorreoDao();

		try {

			assertExisteUsuario(request, login);

			assertValidSession(request, password);

			if (user.getRol().equals("Cliente")) {

				mailList = mailsDao.getLoginCarpetaCorreos(login, 1);

				request.setAttribute("mailList", mailList);

				request.setAttribute("tittle", "Sent Mail");

			} else {

				if (user.getRol().equals("Administrador")) {

					activeUserList = usersDao.getUsuariosActivos();

					inactiveUserList = usersDao.getUsuariosInactivos();

					request.setAttribute("activeUserList", activeUserList);

					request.setAttribute("inactiveUserList", inactiveUserList);

					request.setAttribute("tittle", "Users");

				}
			}

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
		if (Check.checkSession(user, password) == -1) {
			Check.throwError(request, "The introduced password is not correct");
		} else {
			if (Check.checkSession(user, password) == -2) {
				Check.throwError(request, "The introduced user is deactivated");
			}
		}
	}

	private void assertExisteUsuario(HttpServletRequest request, String login)
			throws SQLException, BusinessException {
		this.user = Check.getUsuarioByLogin(login);
		if (user == null) {
			Check.throwError(request, "The introduced user does not exist");
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
