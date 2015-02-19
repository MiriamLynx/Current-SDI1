package com.sdi.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;

import com.sdi.model.Contacto;
import com.sdi.model.Correo;
import com.sdi.persistence.CorreoDao;
import com.sdi.persistence.exception.AlreadyPersistedException;
import com.sdi.persistence.exception.NotPersistedException;
import com.sdi.persistence.exception.PersistenceException;

public class CorreoJdbcDao implements CorreoDao {

	private Connection con = null;

	@Override
	public List<Correo> getCorreos() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Correo> correos = new ArrayList<Correo>();

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("select * from public.correos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Correo correo = new Correo();
				correo.setId(rs.getInt("ID"));
				correo.setFechahora(rs.getLong("FECHAHORA"));
				correo.setAsunto(rs.getString("ASUNTO"));
				correo.setCuerpo(rs.getString("CUERPO"));
				correo.setCarpeta(rs.getInt("CARPETA"));
				correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

				correos.add(correo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return correos;
	}

	public List<Contacto> getDestinatariosCorreo(int idCorreo) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Contacto> contactos = new ArrayList<Contacto>();

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("SELECT * FROM \"PUBLIC\".\"CONTACTOS\" INNER JOIN \"PUBLIC\".\"DESTINATARIOS\" on CONTACTOS.ID = DESTINATARIOS.ID_CONTACTO where DESTINATARIOS.ID_CORREO ="
					+ idCorreo);
			rs = ps.executeQuery();

			while (rs.next()) {
				Contacto contacto = new Contacto();
				contacto.setId(rs.getInt("ID"));
				contacto.setEmail(rs.getString("EMAIL"));
				contacto.setNombre(rs.getString("NOMBRE"));
				contacto.setApellidos(rs.getString("APELLIDOS"));
				contacto.setDireccion(rs.getString("DIRECCION"));
				contacto.setCiudad(rs.getString("CIUDAD"));
				contacto.setAgenda_Usuario(rs.getString("AGENDA_USUARIO"));

				contactos.add(contacto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return contactos;
	}

	@Override
	public List<Correo> getLoginCorreos(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Correo> correos = new ArrayList<Correo>();

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("select * from public.correos where(LOGIN_USUARIO='"
					+ login + "')");
			rs = ps.executeQuery();

			while (rs.next()) {
				Correo correo = new Correo();
				correo.setId(rs.getInt("ID"));
				correo.setFechahora(rs.getLong("FECHAHORA"));
				correo.setAsunto(rs.getString("ASUNTO"));
				correo.setCuerpo(rs.getString("CUERPO"));
				correo.setCarpeta(rs.getInt("CARPETA"));
				correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

				correos.add(correo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return correos;
	}

	@Override
	public List<Correo> getLoginCarpetaCorreos(String login, int carpeta) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Correo> correos = new ArrayList<Correo>();

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("select * from public.correos where(LOGIN_USUARIO='"
					+ login + "' AND CARPETA=" + carpeta + ")");
			rs = ps.executeQuery();

			while (rs.next()) {
				Correo correo = new Correo();
				correo.setId(rs.getInt("ID"));
				correo.setFechahora(rs.getLong("FECHAHORA"));
				correo.setAsunto(rs.getString("ASUNTO"));
				correo.setCuerpo(rs.getString("CUERPO"));
				correo.setCarpeta(rs.getInt("CARPETA"));
				correo.setLogin_Usuario(rs.getString("LOGIN_USUARIO"));

				correos.add(correo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return correos;
	}

	@Override
	public int save(Correo a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		int id_insertado = 0;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement(
					"insert into correos (fechahora, asunto, cuerpo, carpeta, login_usuario) "
							+ "values (?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setDouble(1, a.getFechahora());
			ps.setString(2, a.getAsunto());
			ps.setString(3, a.getCuerpo());
			ps.setInt(4, a.getCarpeta());
			ps.setString(5, a.getLogin_Usuario());

			int updated = ps.executeUpdate();
			if (updated != 1) {
				throw new AlreadyPersistedException("Correo " + a
						+ " already persisted.");
			}

			if (updated == 1) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					id_insertado = generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
		return id_insertado;

	}

	@Override
	public void update(Correo a) throws NotPersistedException {
		PreparedStatement ps = null;
		int rows = 0;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("update correos "
					+ "set fechahora = ?, asunto = ?, cuerpo = ?, carpeta = ?, login_usuario = ?"
					+ " where id=?");

			ps.setDouble(1, a.getFechahora());
			ps.setString(2, a.getAsunto());
			ps.setString(3, a.getCuerpo());
			ps.setInt(4, a.getCarpeta());
			ps.setString(5, a.getLogin_Usuario());
			ps.setLong(6, a.getId());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Correo " + a + " not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public void delete(int id) throws NotPersistedException {
		PreparedStatement ps = null;
		int rows = 0;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("delete from correos where id = ?");

			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Correo " + id + " not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void deleteCorreosLogin(String login) {
		PreparedStatement ps = null;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("delete from correos where login_usuario = ?");

			ps.setString(1, login);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void deleteCorreos() {
		PreparedStatement ps = null;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("delete from correos");

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void reiniciaID() {
		PreparedStatement ps = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement("ALTER TABLE PUBLIC.CORREOS ALTER COLUMN 'ID' RESTART WITH 1");

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

}
