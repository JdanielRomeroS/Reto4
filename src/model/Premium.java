package model;

public class Premium extends User{
	private int id;
	private String fechaVencimiento;
	
	public Premium() {
	}

	public Premium(int id, String nombre, String correo, String contrasenia, String tipo, String fechaVencimiento) {
		super(id, nombre, correo, contrasenia, tipo);
		this.id = id;
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	
	
	
}
