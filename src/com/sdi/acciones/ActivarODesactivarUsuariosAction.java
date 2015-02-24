package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.NotPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class ActivarODesactivarUsuariosAction implements Accion {

	private int active;
	private String[] logins;

	public ActivarODesactivarUsuariosAction(int active) {
		this.active = active;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		getLogins(request);

		List<Usuario> activeUserList;
		List<Usuario> inactiveUserList;

		try {

			UsuarioDao usersDao = Factories.persistence.createUsuarioDao();

			Usuario user = null;

			if (logins != null) {

				for (String s : logins) {
					user = usersDao.findByLogin(s);
					user.setActivo(active());
					usersDao.update(user);
				}

			}

			activeUserList = usersDao.getUsuariosActivos();

			inactiveUserList = usersDao.getUsuariosInactivos();

			request.setAttribute("activeUserList", activeUserList);

			request.setAttribute("inactiveUserList", inactiveUserList);

			request.setAttribute("tittle", "Users");

		} catch (PersistenceException | NotPersistedException e) {
			return "FRACASO";
		}

		return "EXITO";
	}

	private boolean active() {
		if (this.active == 1) {
			return true;
		}
		return false;
	}

	private void getLogins(HttpServletRequest request) {
		if (this.active == 1) {
			this.logins = request.getParameterValues("toActivate");
		} else {
			this.logins = request.getParameterValues("toDeactivate");
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
