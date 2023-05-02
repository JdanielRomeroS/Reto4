package controller;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IController;
import model.GestorCanciones;
import model.GestorPlaylist;
import model.GestorUsuarios;
import model.Playlist;
import model.Song;
import model.User;
import repository.PlaylistRepository;
import repository.SongReposiroty;
import repository.UserRepository;

public class Controller implements IController{
	private static GestorUsuarios listaTodosUsuarios;
	private static GestorCanciones listaTodasCanciones;
	private static GestorPlaylist listaTodasPlaylist;
	public Controller() {

	}
	
	
	public User getUserData(int id) {
		User user = UserRepository.getInfoUser(id);
		return user;
	}
	
	public void setAllUser() {
		listaTodosUsuarios = new GestorUsuarios(UserRepository.getAllUsers());
		
	}
	
	public void setAllSong() {
		listaTodasCanciones = new GestorCanciones(SongReposiroty.getAllsongs());
		
	}
	
	public void setAllPlaylist() {
		listaTodasPlaylist = new GestorPlaylist(PlaylistRepository.listaPlaylist());
		
	}
	
	public GestorPlaylist getAllPlaylist() {
		return listaTodasPlaylist;
		
	}
	
	public GestorCanciones getAllSongs() {
		return listaTodasCanciones;
		
	}
	
	public GestorUsuarios getAllUsers() {
		return listaTodosUsuarios;
		
	}
	
	public GestorPlaylist getPlaylistbyUser(int id) {
		
		List<Playlist> listaPlaylist = listaTodasPlaylist.getListaPlylist();
		List<Playlist> listaMisPlaylist = new ArrayList<>();
		for(Playlist playlistActual : listaPlaylist) {
			if(playlistActual.getIdUser()==id) {
				listaMisPlaylist.add(playlistActual);
			}
		}
		GestorPlaylist listaMyPlaylist = new GestorPlaylist(listaMisPlaylist);
		return listaMyPlaylist;
		
	}
	public User getUserbyCredentials(String correo, String contrasena) {
		List<User> listaUsuarios = listaTodosUsuarios.getListaUsuarios();
		User usuariologed = new User();
		for(User usuarioActual : listaUsuarios) {
			if((usuarioActual.getCorreo().equals(correo))&&(usuarioActual.getContrasenia().equals(contrasena))) {
				return usuarioActual;
			}
			
		}
		
		return usuariologed;
		
	}
	
	
	
}
