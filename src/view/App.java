package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Playlist;
import model.Song;
import model.User;

public class App extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		controller = new Controller();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = (JPanel) getContentPane();

		mostrarLogin();
	}
	
	public void mostrarLogin() {
		setBounds(100, 100, 450, 350);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelLogin panelLogin = new PanelLogin(controller);
		getContentPane().add(panelLogin);
		getContentPane().revalidate();
	}
	
	public void cambiarPanelHome(User user) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelHome panelHome = new PanelHome(controller, user);
		getContentPane().add(panelHome);
		getContentPane().revalidate();
	}
	
	public void cambiarPanelDescubrir(User user, Playlist playlistSeleccionada) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelDescubrir panelDescubrir = new PanelDescubrir(controller, user, playlistSeleccionada);
		getContentPane().add(panelDescubrir);
		getContentPane().revalidate();
	}
	
	public void cambiarPanelVerPlaylist(User user, Playlist playlistSeleccionada, Playlist playlistSeleccionada2) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelVerPlaylist panelVerPlaylist = new PanelVerPlaylist(controller, user, playlistSeleccionada, playlistSeleccionada2);
		getContentPane().add(panelVerPlaylist);
		getContentPane().revalidate();
	}

	public void cambiarPanelAnyadirPlaylist(User user) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelAnyadirPlaylist panelAnyadirPlaylist = new PanelAnyadirPlaylist(controller, user);
		getContentPane().add(panelAnyadirPlaylist);
		getContentPane().revalidate();
	}

	public void cambiarPanelVerUsuario(User user, User userSeleccionada, Playlist playlistSeleccionada) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelVerUsuario panelVerUsuario = new PanelVerUsuario(controller, user, userSeleccionada, playlistSeleccionada);
		getContentPane().add(panelVerUsuario);
		getContentPane().revalidate();
	}

	public void cambiarPanelResgister() {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelRegister panelRegister = new PanelRegister(controller);
		getContentPane().add(panelRegister);
		getContentPane().revalidate();
	}

	public void cambiarPanelElegirPlaylist(User user, Song songSeleccionada) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelElegirPlaylist panelElegirPlaylist = new PanelElegirPlaylist(controller, user, songSeleccionada);
		getContentPane().add(panelElegirPlaylist);
		getContentPane().revalidate();
	}

	public void mostrarPanelDespedida(User user) {
		
        setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelDespedida panelDespedida = new PanelDespedida(user);
		getContentPane().add(panelDespedida);
		getContentPane().revalidate();
        
	}

	
}
