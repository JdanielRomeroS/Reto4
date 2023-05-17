package model;

public class ArtistaAlbum {
	int idArtista;
	int idAlbum;
	
	public ArtistaAlbum() {
	}

	public ArtistaAlbum(int idArtista, int idAlbum) {
		this.idArtista = idArtista;
		this.idAlbum = idAlbum;
	}

	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
	
}
