package com.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.model.Contacto;
import com.sdi.model.Usuario;

public class VerContactosAction implements Accion {

	List<Contacto> contactsList;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		contactsList = user.getContactos();

		request.setAttribute("contactsList", contactsList);

		request.setAttribute("tittle", "Contacts");

		return "EXITO";

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}