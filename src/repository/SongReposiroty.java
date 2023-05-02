package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Enums.Genero;
import model.Song;
import utils.DBUtils;
import utils.SQLQuerys;

public class SongReposiroty {
	public static List<Song> getAllsongs() {
		List<Song> response = new ArrayList<Song>();
		
		String sql = SQLQuerys.GET_ALL_SONGS;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				Integer id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				Integer duracion = resultSet.getInt("duracion");
				Genero genero = Genero.valueOf(resultSet.getString("genero"));
				
				
				Song song = new Song(id, nombre, duracion, genero);
				response.add(song);
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
