package view;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDespedida extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelDespedida(User user) {
		setLayout(null);
		
		ImageIcon icono = new ImageIcon("imagenes/lobo.png");
		Image imagenOriginal = icono.getImage();
		int nuevoAncho = 80;
		int nuevoAlto = 80;
		Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		JLabel labelImagen = new JLabel(new ImageIcon(imagenRedimensionada));
		labelImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        app.mostrarLogin();
			}
		});
		labelImagen.setBounds(169, 94, nuevoAncho, nuevoAlto);
		add(labelImagen);
		
		JLabel lblNewLabel = new JLabel("nos vemos " + user.getNombre());
		lblNewLabel.setBounds(167, 53, 123, 16);
		add(lblNewLabel);
		
		
	}
}
