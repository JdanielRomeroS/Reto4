package view;

import javax.swing.JPanel;

import controller.Controller;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelRegister extends JPanel {
	private JTextField textCorreo;
	private JTextField textNombre;
	private JTextField textContrsena;
	private JTextField textContrsena2;

	/**
	 * Create the panel.
	 * @param controller 
	 */
	public PanelRegister(Controller controller) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setBounds(196, 6, 61, 16);
		add(lblNewLabel);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(48, 52, 61, 16);
		add(lblCorreo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(48, 98, 61, 16);
		add(lblNombre);
		
		JLabel lblContrsena = new JLabel("Contraseña");
		lblContrsena.setBounds(48, 151, 71, 16);
		add(lblContrsena);
		
		JLabel lblRepetirContrasena = new JLabel("Repetir Contraseña");
		lblRepetirContrasena.setBounds(48, 195, 119, 16);
		add(lblRepetirContrasena);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(193, 47, 189, 26);
		add(textCorreo);
		textCorreo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(193, 93, 189, 26);
		add(textNombre);
		textNombre.setColumns(10);
		
		textContrsena = new JTextField();
		textContrsena.setBounds(196, 146, 189, 26);
		add(textContrsena);
		textContrsena.setColumns(10);
		
		textContrsena2 = new JTextField();
		textContrsena2.setBounds(193, 190, 189, 26);
		add(textContrsena2);
		textContrsena2.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isValid = controller.validateEmail(textCorreo.getText());
				boolean esCompleja = controller.verificarComplejidadContrasena(textContrsena.getText());
				boolean sonIguales = controller.compararContrasenas(textContrsena.getText(), textContrsena2.getText());

				if (isValid && esCompleja && sonIguales) {
				    System.out.println("¡Felicidades!");
				} else if (!isValid) {
				    JOptionPane.showMessageDialog(null, "El correo debe ser válido");
				} else if (!esCompleja) {
				    JOptionPane.showMessageDialog(null, "La contraseña debe ser compleja. Mínimo: 8 caracteres, 1 número y una mayúscula");
				} else {
				    JOptionPane.showMessageDialog(null, "Las contraseñas deben ser iguales");
				}
			}
		});
		btnNewButton.setBounds(176, 228, 117, 29);
		add(btnNewButton);
		
		
		
		
		
	}
}
