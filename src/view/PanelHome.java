package view;

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
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHome extends JPanel {
    private JTable jTable;
    private JScrollPane scroll;
    private JTextField textField_1;

    /**
     * Create the panel.
     */
    public PanelHome(Controller controller, User user) {
        setLayout(null);

        GestorPlaylist listaMisPlaylist = controller.getPlaylistbyUser(user.getId());
        List<Playlist> listaPlaylist = listaMisPlaylist.getListaPlylist();

        DefaultTableModel modelo = generarModelo(listaPlaylist);
        jTable = new JTable (modelo);
        jTable.getTableHeader().setFont(new Font("arial",1,13));
        jTable.setDefaultEditor(Object.class, null);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll = new JScrollPane(jTable);
        scroll.setBounds(10, 59, 430, 153);
        add(scroll);

        JLabel lblNewLabel = new JLabel("Home de "+ user.getNombre());
        lblNewLabel.setBounds(10, 3, 129, 16);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Playlist Name");
        lblNewLabel_1.setBounds(10, 31, 96, 16);
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
        textField_1.setBounds(102, 26, 209, 26);
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
        btnSearchPlaylist.setBounds(328, 26, 117, 29);
        add(btnSearchPlaylist);
        
        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int row = jTable.getSelectedRow();
                if (row != -1) {
                    Playlist playlistSeleccionada = listaPlaylist.get(jTable.convertRowIndexToModel(row));
                    int idPlaylistSeleccionada = playlistSeleccionada.getId();
                    System.out.println("ID de la playlist seleccionada: " + idPlaylistSeleccionada);
                }else {
                	JOptionPane.showMessageDialog(null, "Selecciona alguna playlist");
                }
            }
        });
        btnVer.setBounds(102, 224, 117, 29);
        add(btnVer);
        
        JButton btnAddPlaylist = new JButton("Add Playlist");
        btnAddPlaylist.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//TODO esto despues de implementar la busqueda General
        		
        	}
        });
        btnAddPlaylist.setBounds(230, 224, 117, 29);
        add(btnAddPlaylist);
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
