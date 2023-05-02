package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.Controller;
import model.GestorCanciones;
import model.GestorPlaylist;
import model.GestorUsuarios;
import model.Playlist;
import model.Song;
import model.User;

public class PanelDescubrir extends JPanel {
	 private JTable jTable;
	 private JScrollPane scroll;
	 private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public PanelDescubrir(Controller controller, User user) {
		 setLayout(null);

	        GestorPlaylist listaTodasList = controller.getAllPlaylist();
	        GestorCanciones listaTodasCanciones = controller.getAllSongs();
	        GestorUsuarios listaTodosUsusarios = controller.getAllUsers();
	        
	        List<Playlist> listaPlaylist = listaTodasList.getListaPlylist();
	        List<Song> listaSongs = listaTodasCanciones.getListaCanciones();
	        List<User> listaUsers = listaTodosUsusarios.getListaUsuarios();

	        DefaultTableModel modelo = generarModelo(listaPlaylist, listaSongs, listaUsers);
	        jTable = new JTable (modelo);
	        jTable.getTableHeader().setFont(new Font("arial",1,13));
	        jTable.setDefaultEditor(Object.class, null);
	        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        scroll = new JScrollPane(jTable);
	        scroll.setBounds(10, 59, 319, 153);
	        add(scroll);

	        JLabel lblNewLabel = new JLabel("Descubre");
	        lblNewLabel.setBounds(10, 3, 129, 16);
	        add(lblNewLabel);

	        JLabel lblNewLabel_1 = new JLabel("Artist ,song or playlist");
	        lblNewLabel_1.setBounds(10, 31, 150, 16);
	        add(lblNewLabel_1);

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
	                app.cambiarPanelDescubrir(user);
	        		
	        	}
	        });
	        btnNSearchGlobal.setBounds(230, 265, 210, 35);
	        add(btnNSearchGlobal);

	        JButton btnLogout = new JButton("Logout");
	        btnLogout.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Component component = (Component) e.getSource();
			        App app = (App) SwingUtilities.getRoot(component);
			        app.mostrarLogin();
	        	}
	        });
	        btnLogout.setBounds(328, -2, 117, 29);
	        add(btnLogout);

	        textField_1 = new JTextField();
	        textField_1.setBounds(159, 26, 173, 26);
	        add(textField_1);
	        textField_1.setColumns(10);

	        JButton btnSearch = new JButton("Buscar");
	        btnSearch.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	String text = textField_1.getText();
	                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelo);
	                sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + text));
	                jTable.setRowSorter(sorter);
	                
	                
	            	
	            }
	        });
	        btnSearch.setBounds(328, 26, 117, 29);
	        add(btnSearch);
	        
	        JButton btnNewButton = new JButton("Ver");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		int row = jTable.getSelectedRow();
	                if (row != -1) {
	                	int id;
	                	String tipo = jTable.getValueAt(row, 1).toString();
	                	
	                	if(tipo.equals("Song")) {
	                		Song songSeleccionada = listaSongs.get(jTable.convertRowIndexToModel(row));
	                		id = songSeleccionada.getId();     	
	                	}else if (tipo.equals("Playlist")) {
	                		Playlist playlistSeleccionada = listaPlaylist.get(jTable.convertRowIndexToModel(row)-listaSongs.size());
	                		id = playlistSeleccionada.getId();
	                	}else {
	                		User userSeleccionada = listaUsers.get((jTable.convertRowIndexToModel(row)-listaSongs.size())-listaPlaylist.size());
	                		id = userSeleccionada.getId();
	                	}
	                	System.out.println(id);
	                    
	                }else {
	                	JOptionPane.showMessageDialog(null, "Selecciona alguna playlist");
	                }
	        	}
	        });
	        btnNewButton.setBounds(103, 224, 117, 29);
	        add(btnNewButton);
	       
	    }

	    private DefaultTableModel generarModelo(List<Playlist> listaPlaylist,List<Song>listaSongs, List<User>listaUsuarios) {
	        DefaultTableModel modelo = new DefaultTableModel();
	        modelo.addColumn("Nombre");
	        modelo.addColumn("Tipo");
	        
	        
	        for (Object objeto : Stream.of(listaSongs, listaPlaylist, listaUsuarios).flatMap(List::stream).toArray()) {
	            if (objeto instanceof Song) {
	                modelo.addRow(new Object[]{((Song) objeto).getNombre(), "Song"});
	            } else if (objeto instanceof Playlist) {
	                modelo.addRow(new Object[]{((Playlist) objeto).getNombre(), "Playlist"});
	            } else if (objeto instanceof User) {
	                modelo.addRow(new Object[]{((User) objeto).getNombre(), "User"});
	            }
	        }

	        return modelo;
	}
}
