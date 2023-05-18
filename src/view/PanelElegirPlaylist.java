package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

public class PanelElegirPlaylist extends JPanel {
	private JTable jTable;
    private JScrollPane scroll;
    private JTextField textField_1;
	/**
	 * Create the panel.
	 */
	public PanelElegirPlaylist(Controller controller, User user, Song songSeleccionada) {
		
		setLayout(null);

        GestorPlaylist listaMisPlaylist = controller.getPlaylistbyUser(user.getId());
        List<Playlist> listaPlaylist = listaMisPlaylist.getListaPlylist();

        DefaultTableModel modelo = generarModelo(listaPlaylist);
        jTable = new JTable (modelo);
        jTable.getTableHeader().setFont(new Font("arial",1,13));
        jTable.setDefaultEditor(Object.class, null);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(jTable);
        scroll.setBounds(10, 88, 430, 153);
        add(scroll);

        JLabel lblNewLabel = new JLabel("Home de "+ user.getNombre());
        lblNewLabel.setBounds(10, 3, 129, 16);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Playlist Name");
        lblNewLabel_1.setBounds(10, 51, 96, 16);
        add(lblNewLabel_1);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	int row = jTable.getSelectedRow();
                if (row != -1) {
                	
                	
                	Playlist playlistSeleccionada = listaPlaylist.get(jTable.convertRowIndexToModel(row));
                	boolean esCreadted = controller.addSongToPlaylist(songSeleccionada, playlistSeleccionada);
            		if (esCreadted) {
            			JOptionPane.showMessageDialog(null, "se esta añadiendo la cancion " + songSeleccionada.getNombre() + " a la playlist " + playlistSeleccionada.getNombre());
            			Controller controller = new Controller();
                        Component component = (Component) e.getSource();
                        App app = (App) SwingUtilities.getRoot(component);
                        app.cambiarPanelDescubrir(user,null);
            		}else {
            			JOptionPane.showMessageDialog(null, "no se puede repetir la cancion " + songSeleccionada.getNombre() + " en la playlist " + playlistSeleccionada.getNombre());
            			
            		}
                }
            	
               

            }
        });
        btnAceptar.setBounds(10, 265, 210, 35);
        add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        app.cambiarPanelDescubrir(user, null);
        	}
        });
        btnCancelar.setBounds(230, 265, 210, 35);
        add(btnCancelar);

        textField_1 = new JTextField();
        textField_1.setBounds(99, 46, 209, 26);
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
        btnSearchPlaylist.setBounds(320, 47, 117, 29);
        add(btnSearchPlaylist);
		
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
