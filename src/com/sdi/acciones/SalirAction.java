package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalirAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().setAttribute("user", null);

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
