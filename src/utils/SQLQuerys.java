package utils;

public class SQLQuerys {
	public static final String GET_ALL_USERS = "select * from usuario";
	public static final String GET_ALL_SONGS = "select * from cancion";
	public static final String GET_ALL_PLAYLIST = "select * from playlist";
	public static final String GET_ALL_ALBUM =  "select * from album"; // X
	public static final String GET_ALL_PLAYLIST_SONG_ID ="select * from playlist_cancion";
	public static final String GET_ALL_ARTIST_SONG_ID ="select * from artista_cancion";
	public static final String GET_ALL_ARTIST_ALBUM_ID = "select * from artista_album"; //X
	
	
	public static final String INSERT_PLAYLIST = "INSERT INTO playlist (nombre, anolanzamiento, genero, duracion, descripcion, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";
	public static final String INSERT_USER = "INSERT INTO usuario (nombre, correo, contrasena, tipo) VALUES (?, ?, ?, ?)";
	public static final String INSERT_SONG_TO_PLAYLIST = "INSERT INTO playlist_cancion (id_playlist, id_cancion) VALUES (?, ?)";
}
