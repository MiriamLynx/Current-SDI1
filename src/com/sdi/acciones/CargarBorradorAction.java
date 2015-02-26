package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.check.Check;
import com.sdi.persistence.exception.BusinessException;

public class CargarBorradorAction implements Accion {

	private String id;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			getId(request);

		} catch (BusinessException e) {
			return "FRACASO";
		}
		return "EXITO";
	}

	private void getId(HttpServletRequest request) throws BusinessException {
		String[] ids = request.getParameterValues("draft");
		if (ids.length > 1) {
			Check.throwError(request, "You can only edit one draft at once");
		}
		this.id = ids[0];
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
