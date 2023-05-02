package model;

public class User {
	private int id;
	private String nombre;
	private String correo;
	private String contrasenia;
	private String tipo;
	
	
	
	public User() {
	}

	public User(int id, String name, String correo, String tipo) {
		this.id = id;
		this.nombre = name;
		this.correo = correo;
		this.tipo = tipo;
	}

	public User(int id, String nombre, String correo, String contrasenia, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

	
	
	
}
