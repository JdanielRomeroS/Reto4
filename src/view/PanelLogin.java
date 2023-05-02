package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Song;
import model.User;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class PanelLogin extends JPanel {
	private JTextField textMail;
	private JTextField textPass;

	/**
	 * Create the panel.
	 */
	public PanelLogin(Controller controller) {
		setLayout(null);
		
		JLabel lbTitle = new JLabel("DAMusic");
		lbTitle.setBounds(189, 27, 61, 16);
		add(lbTitle);
		
		JLabel lbMail = new JLabel("Mail");
		lbMail.setBounds(126, 100, 61, 16);
		add(lbMail);
		
		JLabel lbPass = new JLabel("Password");
		lbPass.setBounds(126, 165, 61, 16);
		add(lbPass);
		
		textMail = new JTextField("daniel@gmail.com");
		textMail.setBounds(199, 95, 130, 26);
		add(textMail);
		textMail.setColumns(10);
		
		textPass = new JTextField("pass");
		textPass.setBounds(199, 160, 130, 26);
		add(textPass);
		textPass.setColumns(10);
		
		JButton btnLogIn = new JButton("LogIn");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = textMail.getText();
				String pass = textPass.getText();
				User user = controller.getUserbyCredentials(mail, pass);
				
				if(user != null) {
					Component component = (Component) e.getSource();
			        App app = (App) SwingUtilities.getRoot(component);
			        String typeUser= user.getTipo();
			        
			        if (typeUser.equals("artista")) {
			        	
			        	System.out.println("hola artista");
			        	
			        }else if(typeUser.equals("premium")){
			        	
			        	System.out.println("hola Premium");
			        	
			        }else {
			        	
			        	app.cambiarPanelHome(user);
			        	
			        	
			        }
			        
				}else {
					JOptionPane.showMessageDialog(null, "Login incorrecto");
				}
				
			}
		});
		btnLogIn.setBounds(157, 218, 117, 29);
		add(btnLogIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(157, 259, 117, 29);
		add(btnRegister);

	}
}
