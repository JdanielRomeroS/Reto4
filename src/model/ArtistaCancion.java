package model;

public class ArtistaCancion {
	int idArtista;
	int idCancion;
	
	
	public ArtistaCancion() {
		super();
	}

	public ArtistaCancion(int idArtista, int idCancion) {
		super();
		this.idArtista = idArtista;
		this.idCancion = idCancion;
	}

	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}
	
	
}
