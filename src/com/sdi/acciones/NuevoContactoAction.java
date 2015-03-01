package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Contacto;
import com.sdi.model.Usuario;
import com.sdi.persistence.ContactoDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class NuevoContactoAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String mail = request.getParameter("mail");
		String city = request.getParameter("city");

		ContactoDao contactsDao = Factories.persistence.createContactoDao();

		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");

			Contacto nuevo = new Contacto();
			nuevo.setNombre(name);
			nuevo.setApellidos(surname);
			nuevo.setDireccion(address);
			nuevo.setEmail(mail);
			nuevo.setCiudad(city);
			nuevo.setAgenda_Usuario(user.getLogin());
			contactsDao.save(nuevo);

			user.getContactos().add(nuevo);

			request.setAttribute("exit",
					"The contact has been succesfully added");

		} catch (AlreadyPersistedException e) {
			return "FRACASO";
		}
		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
