package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Usuario;
import com.sdi.persistence.ContactoDao;

public class VerContactosAction implements Accion {

	List<Contacto> contactsList;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		ContactoDao contactsDao = Factories.persistence.createContactoDao();

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		contactsList = contactsDao.getLoginContactos(user.getLogin());

		if (!user.getRol().equals("Administrador")) {

			List<Contacto> adminContactsList = contactsDao.getAdminContactos();

			join(adminContactsList);

		}

		request.setAttribute("contactsList", contactsList);

		request.setAttribute("tittle", "Contacts");

		return "EXITO";

	}

	private void join(List<Contacto> adminContactsList) {
		for (Contacto c : adminContactsList) {
			contactsList.add(c);
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}