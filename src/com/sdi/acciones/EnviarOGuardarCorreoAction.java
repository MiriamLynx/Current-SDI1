package com.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Correo;
import com.sdi.model.Destinatario;
import com.sdi.model.Usuario;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.DestinatarioDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.NotPersistedException;

public class EnviarOGuardarCorreoAction implements Accion {

	private String option;

	public EnviarOGuardarCorreoAction(String option) {
		this.option = option;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String subject = request.getParameter("subject");
		String[] recipients = request.getParameterValues("recipients");
		String body = request.getParameter("body");

		Usuario user = (Usuario) request.getSession().getAttribute("user");

		DestinatarioDao recipientsDao = Factories.persistence
				.createDestinatarioDao();
		CorreoDao mailsDao = Factories.persistence.createCorreoDao();

		try {

			Correo mail = new Correo();
			mail.setAsunto(subject);
			mail.setCuerpo(body);
			mail.setFechahora(System.currentTimeMillis());

			String action = request.getParameter("action");

			if (action.equals("send")) {
				mail.setCarpeta(1);
			} else {
				mail.setCarpeta(2);
			}
			mail.setLogin_Usuario(user.getLogin());

			int mailId = mailsDao.save(mail);

			mail.setId(mailId);

			for (String s : recipients) {
				Destinatario recipient = new Destinatario();
				recipient.setId_Contacto(Integer.parseInt(s));
				recipient.setId_Correo(mailId);
				recipientsDao.save(recipient);
			}

			user.getCorreos().add(mail);

			if (option.equals("draft")) {
				String id = request.getParameter("id");
				mailsDao.delete(Integer.parseInt(id));
				for (Correo c : user.getCorreos()) {
					if (c.getId() == Integer.parseInt(id)) {
						user.getCorreos().remove(c);
					}
				}
			}

			request.getSession().setAttribute("mailList", user.getEnviados());

			request.getSession().setAttribute("tittle", "Sent Mail");

		} catch (AlreadyPersistedException e) {
			return "FRACASO";
		} catch (NumberFormatException e) {
			return "FRACASO";
		} catch (NotPersistedException e) {
			return "FRACASO";
		}

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
