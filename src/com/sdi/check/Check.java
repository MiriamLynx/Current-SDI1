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
		request.setAttribute("error", error);
		throw new BusinessException(error);
	}

	public static int checkSession(Usuario user, String password) {
		if (!user.getPasswd().equals(password)) {
			return -1;
		} else {
			if (!user.isActivo()) {
				return -2;
			}
		}
		return 0;
	}

	public static boolean checkPassword(String password, String repeatpassword) {
		if (password.equals(repeatpassword)) {
			return true;
		}
		return false;
	}

	public static boolean checkCurrentPassword(Usuario user,
			String currentpassword) {
		if (currentpassword.equals(user.getPasswd())) {
			return true;
		}
		return false;
	}

}
