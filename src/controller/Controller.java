package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Interfaces.IController;
import model.Artist;
import model.ArtistaCancion;
import model.GestorCanciones;
import model.GestorPlaylist;
import model.GestorUsuarios;
import model.Playlist;
import model.PlaylistCancion;
import model.Song;
import model.User;
import repository.PlaylistRepository;
import repository.SongRepository;
import repository.UserRepository;

public class Controller implements IController{
	private static GestorUsuarios listaTodosUsuarios;
	private static GestorCanciones listaTodasCanciones;
	private static GestorPlaylist listaTodasPlaylist;
	private static List<PlaylistCancion> listaTodasPlaylistCanciones;
	private static List<ArtistaCancion> listaTodasArtistasCanciones;
	public Controller() {
		setAllUser();
    	setAllSong();
    	setAllPlaylist();
    	setAllPlaylistSong();
    	setAllArtistaSong();
	}
	
	private void setAllUser() {
		listaTodosUsuarios = new GestorUsuarios(UserRepository.getAllUsers());
		
	}
	
	private void setAllSong() {
		listaTodasCanciones = new GestorCanciones(SongRepository.getAllsongs());
		
	}
	
	private void setAllPlaylist() {
		listaTodasPlaylist = new GestorPlaylist(PlaylistRepository.listaPlaylist());
		
	}
	
	private void setAllPlaylistSong() {
		listaTodasPlaylistCanciones = PlaylistRepository.playlist_canciones();
		
	}
	
	private void setAllArtistaSong() {
		listaTodasArtistasCanciones = SongRepository.getAllArtistaCacnion();
	}
	
	
	public GestorPlaylist getAllPlaylist() {
		return listaTodasPlaylist;
		
	}
	
	public GestorCanciones getAllSongs() {
		return listaTodasCanciones;
		
	}
	
	public GestorUsuarios getAllUsers() {
		return listaTodosUsuarios;
		
	}
	
	public List<PlaylistCancion> getAllPlaylistCancion(){
		return listaTodasPlaylistCanciones;
		
	}
	
	public List<ArtistaCancion> getAllArtistaCancion(){
		return listaTodasArtistasCanciones;
	}
	
	public GestorPlaylist getPlaylistbyUser(int id) {
		
		List<Playlist> listaPlaylist = listaTodasPlaylist.getListaPlylist();
		List<Playlist> listaMisPlaylist = new ArrayList<>();
		for(Playlist playlistActual : listaPlaylist) {
			if(playlistActual.getIdUser()==id) {
				listaMisPlaylist.add(playlistActual);
			}
		}
		GestorPlaylist listaMyPlaylist = new GestorPlaylist(listaMisPlaylist);
		return listaMyPlaylist;
		
	}
	public User getUserbyCredentials(String correo, String contrasena) {
		List<User> listaUsuarios = listaTodosUsuarios.getListaUsuarios();
		User usuariologed = new User();
		for(User usuarioActual : listaUsuarios) {
			if((usuarioActual.getCorreo().equals(correo))&&(usuarioActual.getContrasenia().equals(contrasena))) {
				return usuarioActual;
			}
			
		}
		
		return null;
		
	}
	
	
	public List<Song> getListSongsByPlaylist(int idPlaylist) {
	    List<Song> songs = new ArrayList<>(); // Inicializar como ArrayList en lugar de null
	    for (PlaylistCancion relacionActual : listaTodasPlaylistCanciones) {
	        if (relacionActual.getiPlaylist() == idPlaylist) {
	            int idCancion = relacionActual.getIdCancion();
	            Song cancion = obtenerCancionPorId(idCancion);
	            songs.add(cancion);
	        }
	    }
	    return songs;
	}

	public Song obtenerCancionPorId(int idCancion) {
	    Song cancion = null;
	    for (Song cancionActual : listaTodasCanciones.getListaCanciones()) {
	        if (idCancion == cancionActual.getId()) {

	            cancion = cancionActual;
	            break; // Salir del bucle después de encontrar la canción
	        }
	    }
	    return cancion;
	}

	public boolean anyadirPlaylist(Playlist playlist) {
		if(playlist.getNombre().isEmpty() || playlist.getDescripcion().isEmpty()) {
			return false;
		}else {
			LocalDate fechaActual = LocalDate.now();
			String fechaFormateada = fechaActual.toString();
			playlist.setAnoLanzamiento(fechaFormateada);
			return PlaylistRepository.insert_playlist(playlist);
		}
	}

	public boolean deletePlaylist(Playlist playlistSeleccionada) {
		
		if(PlaylistRepository.delete_playlist(playlistSeleccionada)) {
			return true ;
		}
		return false;
		
	}
	

	public void generarXML(Playlist playlistSeleccionada, List<Song> listaCancionesPlaylist) {
		try {
			Element DAMusic = new Element("DAMusic");
			Document doc = new Document(DAMusic); 
			
			Element playlist = new Element("playlist");
			DAMusic.addContent(playlist);
			
			Element id = new Element("id");
			id.setText( String.valueOf(playlistSeleccionada.getId()) );
			Element nombre = new Element("nombre");
			nombre.setText( playlistSeleccionada.getNombre() );
			Element anoLanzamiento = new Element("anoLanzamiento");
			anoLanzamiento.setText( playlistSeleccionada.getAnoLanzamiento() );
			Element descripcion = new Element("descripcion");
			descripcion.setText( playlistSeleccionada.getDescripcion() );
			Element duracion = new Element("duracion");
			duracion.setText( String.valueOf(playlistSeleccionada.getDuracion()) );
			Element songs = new Element("songs");
			Element iduser = new Element("iduser");
			iduser.setText( String.valueOf(playlistSeleccionada.getIdUser()) );
			Element genero = new Element("genero");
			genero.setText( String.valueOf(playlistSeleccionada.getGenero()) );
			
			playlist.addContent(id);
			playlist.addContent(nombre);
			playlist.addContent(anoLanzamiento);
			playlist.addContent(descripcion);
			playlist.addContent(duracion);
			playlist.addContent(songs);
			playlist.addContent(iduser);
			
			
			for(Song cacnionActual : listaCancionesPlaylist) {
				
				Element song = new Element("song");
				
				Element idSong = new Element("id");
				idSong.setText( String.valueOf(cacnionActual.getId()) );
				Element nombreSong = new Element("nombre");
				nombreSong.setText( cacnionActual.getNombre() );
				Element duracionSong = new Element("duracion");
				duracionSong.setText( String.valueOf(cacnionActual.getDuracion()) );
				Element generoSong = new Element("genero");
				generoSong.setText( String.valueOf(cacnionActual.getGenero()) );
				Element artistSong = new Element("artists");
				
				
				song.addContent(idSong);
				song.addContent(nombreSong);
				song.addContent(duracionSong);
				song.addContent(generoSong);
				song.addContent(artistSong);
				
				songs.addContent(song);
				// TODO esta parte esta harkodeada porque en la base de datos no hay artistas 
				List<Artist> artistas = new ArrayList<>();
				
				Artist artista = new Artist();
				artista.setNombre("alfonso");
				
				artistas.add(artista);
				// hasta aqui
				for(Artist artistaActual : artistas) {
					
					Element artist =new Element("artist");
					
					Element nombreArtist = new Element("nombre");
					nombreArtist.setText(artistaActual.getNombre());
					artist.addContent(nombreArtist);
					
					artistSong.addContent(artist);
				}
			}
			
			XMLOutputter xml = new XMLOutputter();
	        xml.setFormat(Format.getPrettyFormat());
			xml.output(doc, new FileWriter("reproductor.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public boolean esArtista(User userSeleccionada) {
		boolean response = false;
		
		if(userSeleccionada.getTipo().equals("artista")) {
			response = true;
		}
		
		return response;
		
	}

	public List<Song> getCancionesbyArtist(User userSeleccionada) {
		List<Song> songs = new ArrayList<>(); // Inicializar como ArrayList en lugar de null
	    for (ArtistaCancion relacionActual : listaTodasArtistasCanciones) {
	        if (relacionActual.getIdArtista() == userSeleccionada.getId()) {
	            int idCancion = relacionActual.getIdCancion();
	            Song cancion = obtenerCancionPorId(idCancion);
	            songs.add(cancion);
	        }
	    }
		return songs;
	}

	public boolean validateEmail(String email) {
		String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}

	public boolean verificarComplejidadContrasena(String contrasena) {
	    int longitudMinima = 8;
	    boolean tieneMayuscula = false;
	    boolean tieneMinuscula = false;
	    boolean tieneDigito = false;

	    if (contrasena.length() < longitudMinima) {
	        return false;
	    }

	    for (char c : contrasena.toCharArray()) {
	        if (Character.isUpperCase(c))
	            tieneMayuscula = true;
	        else if (Character.isLowerCase(c))
	            tieneMinuscula = true;
	        else if (Character.isDigit(c))
	            tieneDigito = true;
	    }

	    return tieneMayuscula && tieneMinuscula && tieneDigito;
	}

	public boolean compararContrasenas(String contrasena1, String contrasena2) {
	    return contrasena1.equals(contrasena2);
	}

	public boolean regitrarUsuario(User user) {
		
		return UserRepository.addUser(user);
		
	}

	public boolean addSongToPlaylist(Song songSeleccionada, Playlist playlistSeleccionada) {
		
		return PlaylistRepository.addSongToPlaylist(songSeleccionada,playlistSeleccionada);
	}

	
	public int importarXML() {
		int idPlaylistXML = 0;
			
		try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build("reproductor.xml"); 
            Element rootElement = document.getRootElement();
            Element playlistElement = rootElement.getChild("playlist");
            
            String idPlaylist = playlistElement.getChildText("id");
            String nombrePlaylist = playlistElement.getChildText("nombre");
            String anoLanzamiento = playlistElement.getChildText("anoLanzamiento");
            String descripcion = playlistElement.getChildText("descripcion");
            String duracionPlaylist = playlistElement.getChildText("duracion");
            String idUser = playlistElement.getChildText("iduser");
            
            System.out.println("ID de la playlist: " + idPlaylist);
            System.out.println("Nombre de la playlist: " + nombrePlaylist);
            System.out.println("Año de lanzamiento: " + anoLanzamiento);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Duración de la playlist: " + duracionPlaylist);
            System.out.println("ID del usuario: " + idUser);
            System.out.println("-----------------------");
            
            idPlaylistXML = Integer.parseInt(idPlaylist);
            
            Element songsElement = playlistElement.getChild("songs");
            
            // Recorrer las canciones de la playlist
            for (Element songElement : songsElement.getChildren("song")) {
            	String idCancion = songElement.getChildText("id");
                String nombreCancion = songElement.getChildText("nombre");
                String duracionCancion = songElement.getChildText("duracion");
                String generoCancion = songElement.getChildText("genero");
                
                System.out.println("Id de la canción: " + idCancion);
                System.out.println("Nombre de la canción: " + nombreCancion);
                System.out.println("Duración de la canción: " + duracionCancion);
                System.out.println("Género de la canción: " + generoCancion);
                
                Element artistsElement = songElement.getChild("artists");
                
                // Recorrer los artistas de la canción
                for (Element artistElement : artistsElement.getChildren("artist")) {
                    String nombreArtista = artistElement.getChildText("nombre");
                    System.out.println("Nombre del artista: " + nombreArtista);
                }
                
                System.out.println("-----------------------");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
			
		return idPlaylistXML;
	}
	 
	 
}
