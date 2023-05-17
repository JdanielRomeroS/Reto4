package Interfaces;
import java.util.List;
import model.GestorCanciones;
import model.GestorPlaylist;
import model.GestorUsuarios;
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

}
