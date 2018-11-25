import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.InputEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Editori extends JFrame {
	
	

	private JPanel contentPane;
	JEditorPane editorPane = new JEditorPane();
	private JMenuItem mntmTallenna;
	private JMenuItem mntmTietoja;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editori frame = new Editori();
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
	public Editori() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				
				//File tiedosto = new File("C:\\Users\\Matti\\beastieboys.txt");
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				
				
				try {
					
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
						System.out.println(rivi);
					}
			
				} catch (FileNotFoundException p) {
					System.out.println("Tiedostoa ei löydy..");
				}
			
				editorPane.setText(rivi);
			}
		});
		mntmAvaa.setIcon(new ImageIcon(Editori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				System.out.println("Kirjoitettava tiedosto: "+ uusiTiedosto);
				try {
					
					PrintWriter writer = new PrintWriter(uusiTiedosto); // Talleentaa aina .txt
					String sisalto = editorPane.getText();
					
					writer.println( sisalto );
					
					writer.flush();
					writer.close();
					
				} catch (Exception e1) {
					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					e1.printStackTrace();
				}
			}
		});
		mntmTallenna.setIcon(new ImageIcon(Editori.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnTiedosto.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				Scanner lukija = new Scanner(System.in);
				
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				
				String haettava = ("auto");
				int indeksi = sisalto.indexOf(haettava);
				System.out.println("Indeksi: " +indeksi);
				
				editorPane.setSelectionColor(Color.YELLOW);
				
				editorPane.setSelectionStart(indeksi);
				editorPane.setSelectionEnd( indeksi + haettava.length() );
				
				
			}
		});
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String korvaa = editorPane.getText(); 
				String replaceString=korvaa.replaceAll("auto","biili");
				editorPane.setText(replaceString);
			}
		});
		mnMuokkaa.add(mntmKorvaa);
		
		JPanel infoPanel = new JPanel();
		
		JLabel labelText = new JLabel("Matti Isosalo 2018", SwingConstants.CENTER);
		
		infoPanel.add(labelText);

		JButton LinkedIn = new JButton("LinkedIn");

		LinkedIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eg) {
		try {
		    Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/matti-isosalo-4ab97a165/").toURI());
		} catch (Exception eg1) {}
		}});
		infoPanel.add(LinkedIn);
		
		
		JMenu mnTietoja = new JMenu("Tietoja");

		menuBar.add(mnTietoja);
		
		mntmTietoja = new JMenuItem("Tekijän tiedot");
		mntmTietoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 JOptionPane.showMessageDialog(null, infoPanel, "Tietoja", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnTietoja.add(mntmTietoja);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				valintaikkuna.setApproveButtonText("Avaa tiedosto");
				valintaikkuna.setDialogTitle("Tiedoston valinta");

				
				//File tiedosto = new File("C:\\Users\\Matti\\beastieboys.txt");
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				
				
				try {
					
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
						System.out.println(rivi);
					}
			
				} catch (FileNotFoundException p) {
					System.out.println("Tiedostoa ei löydy..");
				}
			
				editorPane.setText(rivi);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Editori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon(Editori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(new ImageIcon(Editori.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Cut-Black.png")));
		toolBar.add(btnNewButton_2);
		
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		
	}

	public JMenuItem getMntmTietoja() {
		return mntmTietoja;
	}
}
