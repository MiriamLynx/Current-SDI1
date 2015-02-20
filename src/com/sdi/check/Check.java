package com.sdi.check;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.exception.BusinessException;

public class Check {

	public static Usuario getUsuarioByLogin(String login) throws SQLException {
		return Factories.persistence.createUsuarioDao().findByLogin(login);
	}

	public static void throwError(HttpServletRequest request, String error)
			throws BusinessException {
		request.setAttribute("ERROR", error);
		throw new BusinessException(error);
	}

	public static boolean checkSession(Usuario user, String password) {
		if (user.getPasswd().equals(password) && user.isActivo()) {
			return true;
		}
		return false;
	}

}
