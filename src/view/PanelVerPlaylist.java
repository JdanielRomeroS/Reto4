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

public class PanelVerPlaylist extends JPanel {
	private JTable jTable;
	 private JScrollPane scroll;
	 private JTextField textField_1;
	/**
	 * Create the panel.
	 * @param vieneDeVerUsuario 
	 */
	public PanelVerPlaylist(Controller controller, User user, Playlist playlistSeleccionada, Playlist playlistSeleccionada2) {
		setLayout(null);
		
		List<Song> ListaCancionesPlaylist = controller.getListSongsByPlaylist(playlistSeleccionada.getId());
		
		
        DefaultTableModel modelo = generarModelo(ListaCancionesPlaylist);
        jTable = new JTable (modelo);
        jTable.getTableHeader().setFont(new Font("arial",1,13));
        jTable.setDefaultEditor(Object.class, null);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(jTable);
        scroll.setBounds(10, 59, 430, 153);
        add(scroll);

        JLabel lblNewLabel = new JLabel("Playlist: ");
        lblNewLabel.setBounds(10, 3, 129, 16);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("song");
        lblNewLabel_1.setBounds(10, 31, 73, 16);
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
		        app.mostrarLogin();
        	}
        });
        btnLogout.setBounds(328, -2, 117, 29);
        add(btnLogout);

        textField_1 = new JTextField();
        textField_1.setBounds(66, 26, 266, 26);
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
        		
        	}
        });
        btnNewButton.setBounds(56, 224, 117, 29);
        add(btnNewButton);
        
        JButton btnAddSong = new JButton("Add Song");
        btnAddSong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		if(user.getId()==playlistSeleccionada.getId()&&playlistSeleccionada2==null) {
        			Component component = (Component) e.getSource();
    		        App app = (App) SwingUtilities.getRoot(component);
    		        app.cambiarPanelDescubrir(user,playlistSeleccionada);
        		}else {
        			int row = jTable.getSelectedRow();
	                if (row != -1) {
	                	int result = JOptionPane.showConfirmDialog(btnAddSong, "estas seguro?", "Warning", JOptionPane.YES_NO_OPTION);
						if(result == 0) {
	                		Song songSeleccionada = ListaCancionesPlaylist.get(jTable.convertRowIndexToModel(row));
	                		// TODO añadir la cancion a la playlist
	                		System.out.println("se esta añadiendo la cancion " + songSeleccionada.getNombre());
	                	}
	                }else {
	                	JOptionPane.showMessageDialog(null, "Selecciona alguna cancion para añadirla a la playlist");
	        			
	                }
        		}
        		
        		
        		
        	}
        });
        btnAddSong.setBounds(167, 224, 117, 29);
        add(btnAddSong);
        
        JButton btnExportXML = new JButton("Export XML");
        btnExportXML.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.generarXML(playlistSeleccionada,ListaCancionesPlaylist);

        		
        	}
        });
        btnExportXML.setBounds(278, 224, 117, 29);
        add(btnExportXML);
       
    }

    private DefaultTableModel generarModelo(List<Song> listaCanciones) {
    	
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Duracion");
        
        for(Song cancionActual:listaCanciones) {
        	
        	
        	String [] row = {cancionActual.getNombre(), String.valueOf(cancionActual.getDuracion())};
        	 modelo.addRow(row);
        }
        
        return modelo;
	}

}
