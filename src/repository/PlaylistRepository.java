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
import model.Album;
import model.Playlist;
import model.PlaylistCancion;
import model.Song;
import utils.DBUtils;
import utils.SQLQuerys;

public class PlaylistRepository {
	
	public static List<Playlist> listaPlaylist(){
		
		List<Playlist>response = new ArrayList<Playlist>();
		
		String sql = SQLQuerys.GET_ALL_PLAYLIST;
		
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
				String anolanzamiento = resultSet.getString("anolanzamiento");
				String descripcion = resultSet.getString("descripcion");
				Integer duracion = resultSet.getInt("duracion");
				Integer idUser = resultSet.getInt("id_usuario");
				Genero genero = Genero.valueOf(resultSet.getString("genero"));
				
				
				Playlist playlist = new Playlist(id, nombre, anolanzamiento,descripcion, duracion, idUser, genero);
				response.add(playlist);
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
	
	
	public static List<Album> listaAlbumes(){ // TODO pendiente 
		List<Album> response = new ArrayList<Album>();
		
		String sql = SQLQuerys.GET_ALL_ALBUM;
		
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
				String fecha = resultSet.getString("idtitulo");
				
				Album album = new Album(id, fecha);
				response.add(album);
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
		}return response;
	}
	
	public static List<PlaylistCancion> playlist_canciones(){
		List<PlaylistCancion>response = new ArrayList<PlaylistCancion>();
		
		String sql = SQLQuerys.GET_ALL_PLAYLIST_SONG_ID;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				Integer idPlaylist = resultSet.getInt("id_playlist");
				Integer idCancion = resultSet.getInt("id_cancion");
				
				PlaylistCancion plyalitsCancion = new PlaylistCancion(idPlaylist, idCancion);		
				response.add(plyalitsCancion);
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
	
	
	public static boolean insert_playlist(Playlist playlist) {
		boolean isCreated = false ;
		String sql = SQLQuerys.INSERT_PLAYLIST;
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, playlist.getNombre());
			preparedStatement.setString(2, playlist.getAnoLanzamiento());
			preparedStatement.setString(3, String.valueOf(playlist.getGenero()));
			preparedStatement.setInt(4, playlist.getDuracion());
			preparedStatement.setString(5, playlist.getDescripcion());
			preparedStatement.setInt(6, playlist.getIdUser());
			
			preparedStatement.executeUpdate();	
			isCreated = true;

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
		
		
		return isCreated;
		
	}


	public static boolean delete_playlist(Playlist playlistSeleccionada) {
		boolean isCreated = false ;
	    String sql = SQLQuerys.DELETE_PLAYLIST;
	    
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        Class.forName(DBUtils.DRIVER);
	        connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, playlistSeleccionada.getId()); // Suponiendo que el ID de la playlist se obtiene con el método getId()
	        
	        preparedStatement.executeUpdate();
	        isCreated = true ;
	        
	    } catch (SQLException sqle) {  
	        System.out.println("Error con la BBDD - " + sqle.getMessage());
	    } catch (Exception e) { 
	        e.printStackTrace();
	        System.out.println("Error genérico - " + e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) { 
	                preparedStatement.close();
	            }
	        } catch(Exception e) { 
	            // No hace falta				
	        }
	        try {
	            if (connection != null) { 
	                connection.close();
	            }
	        } catch(Exception e) { 
	            // No hace falta
	        }
	    }
	    return isCreated;
	}


	public static boolean addSongToPlaylist(Song songSeleccionada, Playlist playlistSeleccionada) {
		boolean isCreated = false ;
		String sql = SQLQuerys.INSERT_SONG_TO_PLAYLIST;
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement  preparedStatement  = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, playlistSeleccionada.getId());
			preparedStatement.setInt(2, songSeleccionada.getId());
			
			preparedStatement.executeUpdate();	
			isCreated = true;

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
		
		
		return isCreated;
	}


	
}
