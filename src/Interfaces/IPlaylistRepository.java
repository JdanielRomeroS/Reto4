package Interfaces;

import java.util.List;

import model.Album;
import model.Playlist;
import model.PlaylistCancion;
import model.Song;

public interface IPlaylistRepository {
	List<Playlist> listaPlaylist();
	List<Album> listaAlbumes();
	List<PlaylistCancion> playlist_canciones();
	boolean insert_playlist(Playlist playlist);
	boolean delete_playlist(Playlist playlistSeleccionada);
	boolean addSongToPlaylist(Song songSeleccionada, Playlist playlistSeleccionada);
}
