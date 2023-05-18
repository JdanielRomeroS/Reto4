package view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import Enums.Genero;
import controller.Controller;
import model.Playlist;
import model.User;

public class PanelAnyadirPlaylist extends JPanel {
	
    private JTextField textNombre;
    private JTextField textDescripcion;

	/**
	 * Create the panel.
	 * @param user 
	 * @param controller 
	 */
	public PanelAnyadirPlaylist(Controller controller, User user) {
		setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Añadir Playlist "+ user.getNombre());
        lblNewLabel.setBounds(10, 3, 176, 16);
        add(lblNewLabel);

        JLabel lblNombrePlaylist = new JLabel("Playlist Name");
        lblNombrePlaylist.setBounds(37, 65, 96, 16);
        add(lblNombrePlaylist);

        textNombre = new JTextField();
        textNombre.setBounds(180, 60, 209, 26);
        add(textNombre);
        textNombre.setColumns(10);
        
        
        JLabel lblGenero = new JLabel("Playlist Genero ");
        lblGenero.setBounds(36, 115, 110, 16);
        add(lblGenero);
        
        JComboBox comboBox = new JComboBox(Genero.values());
        comboBox.setBounds(181, 111, 209, 27);
        add(comboBox);
        
        
        JLabel lblDescripcion = new JLabel("Descripcion ");
        lblDescripcion.setBounds(37, 167, 96, 16);
        add(lblDescripcion);
        
        textDescripcion = new JTextField();
        textDescripcion.setBounds(180, 162, 209, 26);
        add(textDescripcion);
        textDescripcion.setColumns(10);
        
        
        
        JButton btnAddPlaylist = new JButton("Add Playlist");
        btnAddPlaylist.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String nombrePlaylist = textNombre.getText();
        		Genero genero = (Genero) comboBox.getSelectedItem();
        		String descripcion = textDescripcion.getText();
        		
        		Playlist playlist = new Playlist(nombrePlaylist,descripcion,0,user.getId(),genero);
        		boolean isCreated = controller.anyadirPlaylist(playlist);
        		
        		if (isCreated) {
        			Controller controller = new Controller();
        			JOptionPane.showMessageDialog(null, "La Playlist se a añadido correctamente", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
        			Component component = (Component) e.getSource();
                    App app = (App) SwingUtilities.getRoot(component);
                    app.cambiarPanelHome(user);
        		}else {
        			JOptionPane.showMessageDialog(null, "No se ha podido añadir la playlist", "Anuncio", JOptionPane.INFORMATION_MESSAGE);
        			
        		}
        		
        		
        	}
        });
        btnAddPlaylist.setBounds(170, 224, 117, 29);
        add(btnAddPlaylist);
        
        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.cambiarPanelHome(user);

            }
        });
        btnHome.setBounds(10, 265, 210, 35);
        add(btnHome);

        JButton btnNSearchGlobal = new JButton("Descubrir");
        btnNSearchGlobal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        app.cambiarPanelDescubrir(user, null);
        	}
        });
        btnNSearchGlobal.setBounds(230, 265, 210, 35);
        add(btnNSearchGlobal);
		
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        app.mostrarPanelDespedida(user);
        	}
        });
        btnLogout.setBounds(328, -2, 117, 29);
        add(btnLogout);
        
        
		
	}
}
