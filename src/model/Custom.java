package model;

import java.util.List;

import Enums.Genero;

public class Custom extends Playlist {
	private int id;
	private Boolean privado;
	
	
	public Custom() {
		
	}
	public Custom(int id, String nombre, String anoLanzamiento, String descripcion, int duracion, List<Song> song,
			int iduser, Genero genero, Boolean privado) {
		super(id, nombre, anoLanzamiento, descripcion, duracion, song, iduser, genero);
		
		this.id = id;
		this.privado = privado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getPrivado() {
		return privado;
	}
	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}
	
	
	
	
}
