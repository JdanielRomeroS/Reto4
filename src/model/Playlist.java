package model;

import java.util.List;

import Enums.Genero;

public class Playlist {
	private int id;
	private String nombre;
	private String anoLanzamiento;
	private String descripcion;
	private int duracion;
	public List<Song> song;
	public int iduser;
	private Genero genero;
	
	
	
	public Playlist() {
		
	}

	public Playlist(int id, String nombre, String anoLanzamiento, String descripcion, int duracion, List<Song> song,
			int iduser, Genero genero) {
		this.id = id;
		this.nombre = nombre;
		this.anoLanzamiento = anoLanzamiento;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.song = song;
		this.iduser = iduser;
		this.genero = genero;
	}
	
	

	public Playlist(int id, String nombre, String anoLanzamiento, String descripcion, int duracion, int iduser,
			Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anoLanzamiento = anoLanzamiento;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.iduser = iduser;
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAnoLanzamiento() {
		return anoLanzamiento;
	}

	public void setAnoLanzamiento(String anoLanzamiento) {
		this.anoLanzamiento = anoLanzamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<Song> getSong() {
		return song;
	}

	public void setSong(List<Song> song) {
		this.song = song;
	}

	public int getIdUser() {
		return iduser;
	}

	public void setUser(int iduser) {
		this.iduser = iduser;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
	
}
