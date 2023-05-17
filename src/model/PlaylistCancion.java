package model;

public class PlaylistCancion {
	private int iPlaylist;
	private int idCancion;
	
	public PlaylistCancion() {
		
	}

	public PlaylistCancion(int iPlaylist, int idCancion) {
		this.iPlaylist = iPlaylist;
		this.idCancion = idCancion;
	}
	

	public int getiPlaylist() {
		return iPlaylist;
	}

	public void setiPlaylist(int iPlaylist) {
		this.iPlaylist = iPlaylist;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}
	
	
}
