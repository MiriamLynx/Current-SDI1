package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Correo;
import com.sdi.model.Usuario;
import com.sdi.persistence.CorreoDao;

public class VerEliminadosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		List<Correo> mails;

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		String login = user.getLogin();

		CorreoDao mailsDao = Factories.persistence.createCorreoDao();

		mails = mailsDao.getLoginCarpetaCorreos(login, 3);

		request.getSession().setAttribute("mailList", mails);

		request.getSession().setAttribute("tittle", "Deleted");

		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
