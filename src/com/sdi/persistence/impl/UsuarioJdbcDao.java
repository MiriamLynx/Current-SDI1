package com.sdi.persistence.impl;

import java.sql.*;
import java.util.*;

import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;
import com.sdi.persistence.exception.*;


/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos m��s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Enrique
 *
 */
public class UsuarioJdbcDao implements UsuarioDao {

	public List<Usuario> getUsuarios() throws PersistenceException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from public.usuarios");
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				boolean act = rs.getBoolean("ACTIVO");
				usuario.setActivo(act);
				int ID = rs.getInt("ID_INFOUSUARIO");
				usuario.setId(ID);
				
				
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement("select * from public.infousuarios where(ID='"+ID+"')");
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					usuario.setNombre(rs2.getString("NOMBRE"));
					usuario.setApellidos(rs2.getString("APELLIDOS"));
					usuario.setEmail(rs2.getString("EMAIL"));
				}
				rs2.close();
				ps2.close();
						
				usuarios.add(usuario);
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
		
		return usuarios;
	}

	@Override
	public void save(Usuario a) throws AlreadyPersistedException, PersistenceException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
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
					"insert into public.infousuarios (nombre, apellidos, email) " +
					"values (?, ?, ?)");
			
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getLogin()+"@micorreo.com");
		
				
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("InfoUsuario " + a + " already persisted");
			} 
			
			 ps.close();
			
			
			
			ps = con.prepareStatement("SELECT ID FROM public.INFOUSUARIOS WHERE (EMAIL=" +"'"+ a.getLogin()+"@micorreo.com"+"'"+")");
			rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("ID");
			}
			
			 rs.close();
			 ps.close();
			
			ps = con.prepareStatement(
					"insert into public.usuarios (login, passwd, rol, activo, id_InfoUsuario) " +
					"values (?, ?, ?, ?, ?)");
			
			ps.setString(1, a.getLogin());
			ps.setString(2, a.getPasswd());
			ps.setString(3, a.getRol());
			ps.setString(4, "1");
			ps.setString(5, id+"");
				
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuarioo " + a + " already persisted");
			} 
			
			 ps.close();

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
	public void update(Usuario a) throws NotPersistedException, PersistenceException {
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
					"update public.usuarios " +
					"set passwd = ?, activo = ?" +
					"where login = ?");
			
			ps.setString(1, a.getPasswd());
			ps.setBoolean(2, a.isActivo());
			ps.setString(3, a.getLogin());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuarios " + a + " not found");
			} 
			
			//infousuarios
			PreparedStatement ps2 = null;
			ps2 = con.prepareStatement(
					"update public.infousuarios " +
					"set nombre = ?, apellidos = ?, email = ?" +
					"where ID = ?");
			
			ps2.setString(1, a.getNombre());
			ps2.setString(2, a.getApellidos());
			ps2.setString(3, a.getEmail());
			ps2.setInt(4, a.getId());

			rows = ps2.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("infoUsuarios " + a + " not found");
			} 
			ps2.close();
			
			
			
			
			
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
	public void delete(String login) throws NotPersistedException, PersistenceException {
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
			ps = con.prepareStatement("delete from usuarios where login = ?");
			
			ps.setString(1, login);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + login + " not found");
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
	public Usuario findByLogin(String login) throws PersistenceException{
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from public.usuarios where login = ?");
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setActivo(rs.getBoolean("ACTIVO"));
				usuario.setId(rs.getInt("ID_INFOUSUARIO"));
			}
			
			
			PreparedStatement ps2 = null;
			ResultSet rs2 = null;
			ps2 = con.prepareStatement("select * from public.infousuarios where id = ?");
			if(usuario == null)
				return null;
			ps2.setInt(1, usuario.getId());
			
			rs2 = ps2.executeQuery();
			if (rs2.next()) {
				
	
				usuario.setNombre(rs2.getString("NOMBRE"));
				usuario.setApellidos(rs2.getString("APELLIDOS"));
				usuario.setEmail(rs2.getString("EMAIL"));
			}
			
			
			ps2.close();
			rs2.close();
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuario;
	}
	
	public void deleteUsuarios(){
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
			ps = con.prepareStatement("delete from infousuarios where NOMBRE != 'admin'");
			
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
			ps = con.prepareStatement("ALTER TABLE PUBLIC.INFOUSUARIOS ALTER COLUMN 'ID' RESTART WITH 2");

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

}
