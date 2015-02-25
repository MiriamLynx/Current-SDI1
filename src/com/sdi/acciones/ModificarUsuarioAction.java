package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.check.Check;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.BusinessException;
import com.sdi.persistence.exception.NotPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class ModificarUsuarioAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String currentpassword = request.getParameter("currentpassword");
		String repeatpassword = request.getParameter("repeatpassword");
		String mail = request.getParameter("mail");

		UsuarioDao usersDao = Factories.persistence.createUsuarioDao();

		try {

			Usuario user = (Usuario) request.getSession().getAttribute("user");

			if (user.getRol().equals("Administrador")) {

				user = usersDao.findByLogin(getLogin(mail));

				if (password.length() > 0 && repeatpassword.length() > 0) {
					assertValidPassword(request, password, repeatpassword);
					user.setPasswd(password);
				}

			} else {

				if (password.length() > 0 && repeatpassword.length() > 0
						&& currentpassword.length() > 0) {
					assertValidPassword(request, password, repeatpassword);
					assertCurrentPassword(request, currentpassword, user);
					user.setPasswd(password);
				}

				request.setAttribute("currentpassword", true);

			}

			user.setNombre(name);
			user.setApellidos(surname);

			usersDao.update(user);

			request.setAttribute("exit",
					"The user has been succesfully updated");

			request.setAttribute("profile", user);

		} catch (BusinessException e) {
			return "FRACASO";
		} catch (PersistenceException e) {
			return "FRACASO";
		} catch (NotPersistedException e) {
			return "FRACASO";
		}

		return "EXITO";
	}

	private void assertCurrentPassword(HttpServletRequest request,
			String currentpassword, Usuario user) throws BusinessException {
		if (!Check.checkCurrentPassword(user, currentpassword)) {
			Check.throwError(request,
					"The introduced current password is not correct");
		}
	}

	private void assertValidPassword(HttpServletRequest request,
			String password, String repeatpassword) throws BusinessException {
		if (!Check.checkPassword(password, repeatpassword)) {
			Check.throwError(request, "The introduced passwords do not match");
		}
	}

	private String getLogin(String mail) {
		String[] split = mail.split("@");
		return split[0];
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
