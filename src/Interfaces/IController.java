package Interfaces;
import java.util.List;
import model.GestorCanciones;
import model.GestorPlaylist;
import model.GestorUsuarios;
import model.Playlist;
import model.PlaylistCancion;
import model.Song;
import model.User;


public interface IController {

	GestorPlaylist getAllPlaylist();
	GestorCanciones getAllSongs();
	GestorUsuarios getAllUsers();
	List<PlaylistCancion> getAllPlaylistCancion();
	GestorPlaylist getPlaylistbyUser(int id);
	User getUserbyCredentials(String correo, String contrasena);
	List<Song> getListSongsByPlaylist(int idPlaylist);
	Song obtenerCancionPorId(int idCancion);
	boolean anyadirPlaylist(Playlist playlist);
	boolean deletePlaylist(Playlist playlistSeleccionada);
	void generarXML(Playlist playlistSeleccionada, List<Song> listaCancionesPlaylist);
	boolean esArtista(User userSeleccionada);
	List<Song> getCancionesbyArtist(User userSeleccionada);
	boolean validateEmail(String email);
	boolean verificarComplejidadContrasena(String contrasena);
	boolean compararContrasenas(String contrasena1, String contrasena2);
	boolean addSongToPlaylist(Song songSeleccionada, Playlist playlistSeleccionada);
}
