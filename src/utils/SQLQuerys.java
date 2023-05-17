package utils;

public class SQLQuerys {
	public static final String GET_ALL_USERS = "select * from usuario";
	public static final String GET_ALL_SONGS = "select * from cancion";
	public static final String GET_ALL_PLAYLIST = "select * from playlist";
	public static final String GET_ALL_PLAYLIST_SONG_ID ="select * from playlist_cancion";
	public static final String GET_ALL_ARTIST_SONG_ID ="select * from artista_cancion";
	
	public static final String INSERT_PLAYLIST = "INSERT INTO playlist (nombre, anolanzamiento, genero, duracion, descripcion, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";
}
