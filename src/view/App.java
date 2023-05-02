package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
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
		controller.setAllUser();
    	controller.setAllSong();
    	controller.setAllPlaylist();
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
	
	public void cambiarPanelDescubrir(User user) {
		setBounds(100, 100, 450, 330);
		getContentPane().removeAll();
		getContentPane().invalidate();
		PanelDescubrir panelDescubrir = new PanelDescubrir(controller, user);
		getContentPane().add(panelDescubrir);
		getContentPane().revalidate();
	}
}
