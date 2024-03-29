package com.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

import com.sdi.acciones.Accion;
import com.sdi.acciones.ActivarODesactivarUsuariosAction;
import com.sdi.acciones.EnviarOGuardarCorreoAction;
import com.sdi.acciones.ModificarUsuarioAction;
import com.sdi.acciones.NuevoContactoAction;
import com.sdi.acciones.PerfilAction;
import com.sdi.acciones.RegistrarseAction;
import com.sdi.acciones.SalirAction;
import com.sdi.acciones.ValidarseAction;
import com.sdi.acciones.VerBorradoresAction;
import com.sdi.acciones.VerContactosAction;
import com.sdi.acciones.VerEliminadosAction;
import com.sdi.acciones.VerEnviadosAction;
import com.sdi.acciones.VerUsuariosAction;
import com.sdi.model.Usuario;

public class Controlador extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion,
																// objeto
																// Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol,
																			// <opcion,
																			// <resultado,
																			// JSP>>>

	public void init() throws ServletException {
		crearMapaAcciones();
		crearMapaDeJSP();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		String opcion, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;

		try {
			opcion = req.getServletPath().replace("/", "");

			rolAntes = obtenerRolDeSesion(req);

			accion = buscarAccionParaOpcion(rolAntes, opcion);

			resultado = accion.execute(req, res);

			rolDespues = obtenerRolDeSesion(req);

			jspSiguiente = buscarJSPSegun(rolDespues, opcion, resultado);

		} catch (Exception e) {

			req.getSession().invalidate();

			Log.error("Se ha producido alguna excepción no manejada [%s]", e);

			jspSiguiente = "/login.jsp";
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jspSiguiente);

		dispatcher.forward(req, res);
	}

	private String obtenerRolDeSesion(HttpServletRequest req) {
		HttpSession sesion = req.getSession();
		if (sesion.getAttribute("user") == null)
			return "PUBLICO";
		else if (((Usuario) sesion.getAttribute("user")).getRol().equals(
				"Administrador"))
			return "ADMIN";
		else
			return "REGISTRADO";
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion) {

		Accion accion = mapaDeAcciones.get(rol).get(opcion);
		Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
				opcion, rol);
		return accion;
	}

	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado) {

		String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
				.get(resultado);
		Log.debug(
				"Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",
				jspSiguiente, resultado, opcion, rol);
		return jspSiguiente;
	}

	private void crearMapaAcciones() {

		mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

		Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
		mapaPublico.put("signin", new ValidarseAction());
		mapaPublico.put("signup", new RegistrarseAction());
		mapaDeAcciones.put("PUBLICO", mapaPublico);

		Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
		mapaRegistrado.put("signout", new SalirAction());
		mapaRegistrado.put("sentmail", new VerEnviadosAction());
		mapaRegistrado.put("drafts", new VerBorradoresAction());
		mapaRegistrado.put("deleted", new VerEliminadosAction());
		mapaRegistrado.put("updateprofile", new PerfilAction("profile"));
		mapaRegistrado.put("modifyuser", new ModificarUsuarioAction());
		mapaRegistrado.put("contacts", new VerContactosAction());
		mapaRegistrado.put("newcontact", new NuevoContactoAction());
		mapaRegistrado.put("sendSave", new EnviarOGuardarCorreoAction("new"));
		mapaRegistrado.put("sendUpdateDraft", new EnviarOGuardarCorreoAction("draft"));
		mapaDeAcciones.put("REGISTRADO", mapaRegistrado);

		Map<String, Accion> mapaAdmin = new HashMap<String, Accion>();
		mapaAdmin.put("signout", new SalirAction());
		mapaAdmin.put("users", new VerUsuariosAction());
		mapaAdmin.put("activate", new ActivarODesactivarUsuariosAction(1));
		mapaAdmin.put("deactivate", new ActivarODesactivarUsuariosAction(0));
		mapaAdmin.put("profile", new PerfilAction("admin"));
		mapaAdmin.put("updateprofile", new PerfilAction("profile"));
		mapaAdmin.put("modifyuser", new ModificarUsuarioAction());
		mapaAdmin.put("contacts", new VerContactosAction());
		mapaAdmin.put("newcontact", new NuevoContactoAction());
		mapaDeAcciones.put("ADMIN", mapaAdmin);
	}

	private void crearMapaDeJSP() {

		mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

		Map<String, Map<String, String>> opcionResJSP = new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP = new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put("EXITO", "/inbox.jsp");
		resJSP.put("FRACASO", "/login.jsp");
		opcionResJSP.put("signin", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/welcome.jsp");
		opcionResJSP.put("signout", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/register.jsp");
		resJSP.put("EXITO", "/register.jsp");
		opcionResJSP.put("signup", resJSP);

		mapaDeNavegacion.put("PUBLICO", opcionResJSP);

		opcionResJSP = new HashMap<String, Map<String, String>>();
		resJSP = new HashMap<String, String>();

		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("signin", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("sentmail", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("drafts", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("deleted", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("modifyuser", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/modify.jsp");
		resJSP.put("EXITO", "/modify.jsp");
		opcionResJSP.put("updateprofile", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/contacts.jsp");
		opcionResJSP.put("contacts", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/addContact.jsp");
		resJSP.put("EXITO", "/addContact.jsp");
		opcionResJSP.put("newcontact", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("sendSave", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/mails.jsp");
		opcionResJSP.put("sendUpdateDraft", resJSP);

		mapaDeNavegacion.put("REGISTRADO", opcionResJSP);

		opcionResJSP = new HashMap<String, Map<String, String>>();
		resJSP = new HashMap<String, String>();

		// Mapa de navegación del administrador
		resJSP.put("EXITO", "/users.jsp");
		opcionResJSP.put("signin", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/users.jsp");
		opcionResJSP.put("users", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/users.jsp");
		opcionResJSP.put("activate", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/users.jsp");
		opcionResJSP.put("deactivate", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/users.jsp");
		opcionResJSP.put("profile", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/modify.jsp");
		resJSP.put("FRACASO", "/modify.jsp");
		opcionResJSP.put("modifyuser", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/modify.jsp");
		opcionResJSP.put("updateprofile", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("EXITO", "/contacts.jsp");
		opcionResJSP.put("contacts", resJSP);
		resJSP = new HashMap<String, String>();
		resJSP.put("FRACASO", "/addContact.jsp");
		resJSP.put("EXITO", "/addContact.jsp");
		opcionResJSP.put("newcontact", resJSP);

		mapaDeNavegacion.put("ADMIN", opcionResJSP);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doGet(req, res);
	}

}