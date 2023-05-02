package model;

import java.util.List;

import Enums.Genero;

public class Song {
	private int id;
	private String nombre;
	private int duracion;
	private Genero genero;
	public List<Artist>  artist;
	
	
	public Song() {
	}


	public Song(int id, String nombre, int duracion, Genero genero, List<Artist> artist) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.artist = artist;
	}


	public Song(int id, String nombre, int duracion, Genero genero) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
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


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public List<Artist> getArtist() {
		return artist;
	}


	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}
	
	
	
}
