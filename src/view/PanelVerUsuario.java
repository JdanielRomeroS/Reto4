package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
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
import model.GestorPlaylist;
import model.Playlist;
import model.Song;
import model.User;

public class PanelVerUsuario extends JPanel {
	 private JTable jTable;
	 private JScrollPane scroll;
	 private JTextField textField_1;
	 private DefaultTableModel modelo;

	/**
	 * Create the panel.
	 * @param userSeleccionada 
	 * @param user 
	 * @param controller 
	 * @param playlistSeleccionada 
	 */
	public PanelVerUsuario(Controller controller, User user, User userSeleccionada, Playlist playlistSeleccionada) {
		setLayout(null);
		
		
		boolean esArtista = controller.esArtista(userSeleccionada);
		
		//TODO hay que sacar las Playlist-Albumes del Artista no del Usuario ... sus propias canciones con sus respectivos albumes
		GestorPlaylist listaUserPlaylist = controller.getPlaylistbyUser(userSeleccionada.getId());
        List<Playlist> listaPlaylist = listaUserPlaylist.getListaPlylist();
        List<Song> listaCanciones = controller.getCancionesbyArtist(userSeleccionada);
    	
        
        if (esArtista) {
        	
        	modelo = generarModeloArtista(listaCanciones != null ? listaCanciones : Collections.emptyList(), listaPlaylist != null ? listaPlaylist : Collections.emptyList());

		}else {
			modelo = generarModelo(listaPlaylist);
		}
		
        
		
		jTable = new JTable (modelo);
        jTable.getTableHeader().setFont(new Font("arial",1,13));
        jTable.setDefaultEditor(Object.class, null);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(jTable);
        scroll.setBounds(10, 83, 430, 153);
        add(scroll);

        JLabel lblNewLabel = new JLabel("User: "+ user.getNombre());
        lblNewLabel.setBounds(10, 3, 129, 16);
        add(lblNewLabel);
        
        JLabel lblNameUserSearch = new JLabel("Playlist de : "+ userSeleccionada.getNombre());
		lblNameUserSearch.setBounds(140, 28, 163, 16);
        add(lblNameUserSearch);

        JLabel lblNewLabel_1 = new JLabel("Playlist Name");
        lblNewLabel_1.setBounds(10, 55, 96, 16);
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

        textField_1 = new JTextField();
        textField_1.setBounds(107, 45, 209, 26);
        add(textField_1);
        textField_1.setColumns(10);

        JButton btnSearchPlaylist = new JButton("Buscar");
        btnSearchPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField_1.getText();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelo);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                jTable.setRowSorter(sorter);
            }
        });
        btnSearchPlaylist.setBounds(323, 42, 117, 29);
        add(btnSearchPlaylist);
        
        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        
        		int row = jTable.getSelectedRow();
                if (row != -1) {
                	if (esArtista) {
	                	String tipo = jTable.getValueAt(row, 1).toString();
	                	if(tipo.equals("Song")) {
	                		Song songSeleccionada = listaCanciones.get(jTable.convertRowIndexToModel(row));
	                		// TODO falta mostrar la letra de la cancion
	                		
	                	}else{
	                		Playlist playlistSeleccionada1 = listaPlaylist.get(jTable.convertRowIndexToModel(row)-listaCanciones.size());
	                		app.cambiarPanelVerPlaylist(user, playlistSeleccionada1, playlistSeleccionada);
	                	}
                	}else {
                		Playlist playlistSeleccionada1 = listaPlaylist.get(jTable.convertRowIndexToModel(row));
        		        app.cambiarPanelVerPlaylist(user, playlistSeleccionada1, playlistSeleccionada);
                	}
                }else {
                	JOptionPane.showMessageDialog(null, "Selecciona alguna playlist");
                }
            }
        });
        btnVer.setBounds(164, 238, 129, 29);
        add(btnVer);
        
        
		
		
	}
	
	private DefaultTableModel generarModeloArtista(List<Song> listaCanciones, List<Playlist> listaPlaylist) {
		DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        
        
        for (Object objeto : Stream.of(listaCanciones, listaPlaylist).flatMap(List::stream).toArray()) {
            if (objeto instanceof Song) {
                modelo.addRow(new Object[]{((Song) objeto).getNombre(), "Song"});
            }else{
                modelo.addRow(new Object[]{((Playlist) objeto).getNombre(), "Playlist"});
            }
        }
		return modelo;
	}

	private DefaultTableModel generarModelo(List<Playlist> listaPlaylist) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Duracion");
        modelo.addColumn("Genero");

        for (Playlist PlaylistActual : listaPlaylist) {
            String [] row = {PlaylistActual.getNombre(), String.valueOf(PlaylistActual.getDuracion()), String.valueOf(PlaylistActual.getGenero())};
            modelo.addRow(row);
        }

        return modelo;
    }
}
