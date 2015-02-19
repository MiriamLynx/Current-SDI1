package com.sdi.persistence.impl;

import java.sql.*;
import java.util.*;

import com.sdi.model.Contacto;
import com.sdi.persistence.ContactoDao;
import com.sdi.persistence.exception.*;

public class ContactoJdbcDao implements ContactoDao{
	
	@Override
	public List<Contacto> getContactos() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Contacto> contactos = new ArrayList<Contacto>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from public.contactos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Contacto contacto= new Contacto();
				contacto.setId(rs.getInt("ID"));
				contacto.setEmail(rs.getString("EMAIL"));
				contacto.setNombre(rs.getString("NOMBRE"));
				contacto.setApellidos(rs.getString("APELLIDOS"));
				contacto.setDireccion(rs.getString("DIRECCION"));
				contacto.setCiudad(rs.getString("CIUDAD"));
				contacto.setAgenda_Usuario(rs.getString("AGENDA_USUARIO"));
			
						
				contactos.add(contacto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return contactos;
	}
	
	@Override
	public List<Contacto> getLoginContactos(String login) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Contacto> contactos = new ArrayList<Contacto>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from public.contactos where(AGENDA_USUARIO='"+login+"')");
			rs = ps.executeQuery();

			while (rs.next()) {
				Contacto contacto= new Contacto();
				contacto.setId(rs.getInt("ID"));
				contacto.setEmail(rs.getString("EMAIL"));
				contacto.setNombre(rs.getString("NOMBRE"));
				contacto.setApellidos(rs.getString("APELLIDOS"));
				contacto.setDireccion(rs.getString("DIRECCION"));
				contacto.setCiudad(rs.getString("CIUDAD"));
				contacto.setAgenda_Usuario(rs.getString("AGENDA_USUARIO"));
			
						
				contactos.add(contacto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return contactos;
	}
	
	@Override
	public void save(Contacto a) throws AlreadyPersistedException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into contactos (email, nombre, apellidos, direccion, ciudad, agenda_usuario) " +
					"values (?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellidos());
			ps.setString(4, a.getDireccion());
			ps.setString(5, a.getCiudad());
			ps.setString(6, a.getAgenda_Usuario());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Contacto " + a + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
	}
	
	@Override
	public void update(Contacto a) throws NotPersistedException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update contactos " +
					"set email = ?, nombre = ?, apellidos = ?, direccion = ?, ciudad = ?, agenda_usuario = ?" +
					" where id = ?");
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellidos());
			ps.setString(4, a.getDireccion());
			ps.setString(5, a.getCiudad());
			ps.setString(6, a.getAgenda_Usuario());
			ps.setLong(7, a.getId());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Contacto " + a + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	@Override
	public void delete(int id) throws NotPersistedException {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from contactos where id = ?");
			
			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Contacto " + id + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
	}

	@Override
	public void deleteContactos(){
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from contactos");

			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	@Override
	public void reiniciaID(){
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("ALTER TABLE PUBLIC.CONTACTOS ALTER COLUMN 'ID' RESTART WITH 1");

			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	
	
	@Override
	public void deleteContactosAdmin(){
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from contactos where agenda_usuario = 'admin'");

			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}
	
	@Override
	public List<Contacto> getAdminContactos() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Contacto> contactos = new ArrayList<Contacto>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			/*SELECT * FROM "PUBLIC"."CONTACTOS" inner join  
"PUBLIC"."USUARIOS" on "CONTACTOS"."AGENDA_USUARIO" = "USUARIOS"."LOGIN" where "USUARIOS"."ROL" = 'Administrador'*/
			
			
			ps = con.prepareStatement("SELECT * FROM \"PUBLIC\".\"CONTACTOS\" inner join \"PUBLIC\".\"USUARIOS\" on \"CONTACTOS\".\"AGENDA_USUARIO\" = \"USUARIOS\".\"LOGIN\" where \"USUARIOS\".\"ROL\" = 'Administrador'");
			rs = ps.executeQuery();

			while (rs.next()) {
				Contacto contacto= new Contacto();
				contacto.setId(rs.getInt("ID"));
				contacto.setEmail(rs.getString("EMAIL"));
				contacto.setNombre(rs.getString("NOMBRE"));
				contacto.setApellidos(rs.getString("APELLIDOS"));
				contacto.setDireccion(rs.getString("DIRECCION"));
				contacto.setCiudad(rs.getString("CIUDAD"));
				contacto.setAgenda_Usuario(rs.getString("AGENDA_USUARIO"));
			
						
				contactos.add(contacto);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return contactos;
	}
}
