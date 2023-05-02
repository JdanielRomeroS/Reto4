package utils;

public class SQLQuerys {
	
	public static final String SELECT_USER = "select * from usuario where correo = ? and contrasena = ?";
	public static final String GET_ALL_USERS = "select * from usuario";
	public static final String GET_ALL_SONGS = "select * from cancion";
	public static final String GET_ALL_PLAYLIST = "select * from playlist";
	public static final String GET_ALL_BY_USER = "select * from user where id = ?";
	
}
