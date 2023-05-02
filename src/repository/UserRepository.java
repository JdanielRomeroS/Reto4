package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Enums.Genero;
import model.Song;
import model.User;
import utils.DBUtils;
import utils.SQLQuerys;


public class UserRepository {
	public static User getInfoUser(String mail, String pass) {
		User response = null;
		
		String sql = SQLQuerys.SELECT_USER;
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement  = null;
		try {
			
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, pass);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				String name = resultSet.getString("nombre");
				int id = resultSet.getInt("id");
				String tipo = resultSet.getString("tipo");
				response = new User(id,name, mail, pass, tipo);	
			}
			
		}catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
			try {
				if (preparedStatement != null) { 
					preparedStatement.close();
				}
			} catch(Exception e){ 
				// No hace falta				
			};
			try {
				if (connection != null) { 
					connection.close();
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		
		return response;
		
	}
	
	public static User getInfoUser(int id) {
		User response = null;
		
		String sql = SQLQuerys.SELECT_USER;
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement  = null;
		try {
			
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				String name = resultSet.getString("nombre");
				String correo =  resultSet.getString("correo");
				String tipo = resultSet.getString("tipo");
				response = new User(id,name, correo, tipo);	
			}
			
		}catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
			try {
				if (preparedStatement != null) { 
					preparedStatement.close();
				}
			} catch(Exception e){ 
				// No hace falta				
			};
			try {
				if (connection != null) { 
					connection.close();
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		
		return response;
		
	}

	public static List<User> getAllUsers() {
		List<User> response = new ArrayList<User>();
		
		String sql = SQLQuerys.GET_ALL_USERS;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("nombre");
				String correo =  resultSet.getString("correo");
				String contrasenia = resultSet.getString("contrasena");
				String tipo = resultSet.getString("tipo");
				User user = new User(id,name,correo,contrasenia,tipo);
				response.add(user);
			}
			
			
		}catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
			try {
				if (statement != null) { 
					statement.close();
				}
			} catch(Exception e){ 
				// No hace falta				
			};
			try {
				if (connection != null) { 
					connection.close();
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return response;
	}
	
}
