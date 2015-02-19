package com.sdi.persistence.impl;

import java.sql.*;

import alb.util.jdbc.Jdbc;

import com.sdi.model.Destinatario;
import com.sdi.persistence.DestinatarioDao;
import com.sdi.persistence.exception.*;

public class DestinatarioJdbcDao implements DestinatarioDao {

	private static Connection con = null;

	@Override
	public void save(Destinatario a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		int rows = 0;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("insert into destinatarios (id_correo, id_contacto)"
					+ "values (?, ?);");

			ps.setInt(1, a.getId_Correo());
			ps.setInt(2, a.getId_Contacto());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Destinatario " + a
						+ " already persisted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema"
					+ ps.toString(), e);
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
	public void delete(int id_Correo) throws NotPersistedException {

		PreparedStatement ps = null;
		int rows = 0;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("delete from destinatarios where id_correo = ?");

			ps.setLong(1, id_Correo);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Correo " + id_Correo
						+ " not found");
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
	public void reiniciaID() {
		PreparedStatement ps = null;

		try {

			con = Jdbc.getConnection();

			ps = con.prepareStatement("ALTER TABLE PUBLIC.DESTINATARIOS ALTER COLUMN 'ID' RESTART WITH 1");

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
