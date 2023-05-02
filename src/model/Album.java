package model;

import java.util.List;

import Enums.Genero;

public class Album extends Playlist{
	private int id;
	private String fecha;
	public List<Artist> artist;
	
	public Album() {
	}

	public Album(int id, String nombre, String anoLanzamiento, String descripcion, int duracion, List<Song> song,
			int iduser, Genero genero, String fecha, List<Artist> artista) {
		super(id, nombre, anoLanzamiento, descripcion, duracion, song, iduser, genero);
		this.id = id;
		this.fecha = fecha;
		this.artist = artista;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Artist> getArtist() {
		return artist;
	}

	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}
	
	
	
}
