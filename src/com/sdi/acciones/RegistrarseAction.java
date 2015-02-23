package com.sdi.acciones;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.check.Check;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.BusinessException;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String login = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String repeatpassword = request.getParameter("repeatpassword");

		try {

			assertNoExisteUsuario(request, login);

			assertValidPassword(request, password, repeatpassword);

			Usuario user = new Usuario();
			user.setLogin(login);
			user.setNombre(name);
			user.setApellidos(surname);
			user.setPasswd(password);
			user.setRol("Cliente");

			Factories.persistence.createUsuarioDao().save(user);

			request.setAttribute("exit",
					"The user has been succesfully created");

		} catch (AlreadyPersistedException e) {
			return "FRACASO";
		} catch (BusinessException e) {
			return "FRACASO";
		} catch (SQLException e) {
			return "FRACASO";
		}
		return "EXITO";
	}

	private void assertNoExisteUsuario(HttpServletRequest request, String login)
			throws SQLException, BusinessException {
		Usuario user = Check.getUsuarioByLogin(login);
		if (user != null) {
			Check.throwError(request, "The introduced user already exists");
		}
	}

	private void assertValidPassword(HttpServletRequest request,
			String password, String repeatpassword) throws BusinessException {
		if (!Check.checkPassword(password, repeatpassword)) {
			Check.throwError(request, "The introduced passwords do not match");
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
