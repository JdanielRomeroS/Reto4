package model;

public class Artist extends User {
	private int id;
	private String biografia;
	private String fechaInicio;
	
	
	public Artist() {
	}


	public Artist(int id, String nombre, String correo, String contrasenia, String tipo, String biografia, String fechaInicio) {
		super(id, nombre, correo, contrasenia, tipo);
		this.id = id;
		this.biografia = biografia;
		this.fechaInicio = fechaInicio;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBiografia() {
		return biografia;
	}


	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
	
	
}
