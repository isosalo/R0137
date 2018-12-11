import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.JButton;
	import javax.swing.JTextField;
	import javax.swing.JPanel;
	import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;

	public class Levyhylly extends JFrame {
		
		final static int MAX_QTY = 10; // Code changed here 
		static int dbItems = 0;
		
		static Levy[] myDB = new Levy[MAX_QTY];
		
		static JTable tableRecord; 
		static JButton btnAddRecord; 
		static JButton btnUpdate; 
		static JButton btnDeleteRow; 
		private JMenuBar menuBar;
		private JMenu mnTiedosto;
		private JMenu mnTietoja;
		
		public Levyhylly(){
			super("Levyhylly");

			initiateRecordCollection();
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			BorderLayout layout = new BorderLayout();
			getContentPane().setLayout(layout); 
			setBounds(0,0,840,666); 
			setLocationRelativeTo(null);

			
			tableRecord = new JTable();
			tableRecord.setSelectionBackground(Color.YELLOW);
			tableRecord.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"nimi", "artisti", "julkaisvuosi"
				}
			));
			tableRecord.setBounds(10, 36, 240, 100);
			getContentPane().add(tableRecord, BorderLayout.CENTER);
			
			populateTable();
			
			btnAddRecord = new JButton("Lis‰‰ uusi levy");
			btnAddRecord.setBounds(270, 36, 89, 23);
			EventHandlerAdd commandHandler = new EventHandlerAdd();
			btnAddRecord.addActionListener(commandHandler);

			btnUpdate = new JButton("P‰ivit‰ taulukko");
			btnUpdate.setBounds(270, 36, 89, 23);
			EventHandlerUpdate commandHandler2 = new EventHandlerUpdate();
			btnUpdate.addActionListener(commandHandler2);

			btnDeleteRow = new JButton("Poista valittu rivi");
			btnDeleteRow.setBounds(270, 36, 89, 23);
			EventHandlerDelete commandHandler3 = new EventHandlerDelete();
			btnDeleteRow.addActionListener(commandHandler3);

			JPanel buttonPanel = new JPanel();
			buttonPanel.add(btnAddRecord);
			buttonPanel.add(btnUpdate);
			buttonPanel.add(btnDeleteRow);

			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			mnTiedosto = new JMenu("TIedosto");
			menuBar.add(mnTiedosto);
			
			mnTietoja = new JMenu("Tietoja");
			menuBar.add(mnTietoja);

		}

		private void populateTable() {
			
			
			for (int row=0; row<dbItems; row++){
				tableRecord.setValueAt(myDB[row].getNimi(), row, 0);  
				tableRecord.setValueAt(myDB[row].getArtisti(), row, 1);  
				tableRecord.setValueAt(myDB[row].getJulkaisuvuosi(), row, 2);
			}
			
		}
		
		private void initiateRecordCollection(){
			
			try {
				 
	            // En saa tietokantayhteytt‰ toimimaan freemysql:‰‰n.
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost, 127.0.0.1");
	           // Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268747", "sql7268747", "bkl334CCZ");
		        Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM levy");
	            for (int i = 0; rs.next(); i++) {
				myDB[i] = new Levy(rs.getString(1), rs.getString(2), rs.getString(3));
				dbItems=i+1;
				
				}
				
				con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
			
			
		}
		
		private class EventHandlerAdd implements ActionListener
		{
			public void actionPerformed (ActionEvent addingEvent) 
			{
				if (addingEvent.getSource() == btnAddRecord){
					if (dbItems < MAX_QTY){
						getNewRecordFromUser();
						populateTable();
						JOptionPane.showInputDialog(null, "Tallennettu uusi levy",JOptionPane.INFORMATION_MESSAGE);
					}
					
						
				}
			}
			}
	
		private class EventHandlerUpdate implements ActionListener
		{
			public void actionPerformed (ActionEvent myEvent) 
			{
				if (myEvent.getSource() == btnUpdate){
						initiateRecordCollection();
						populateTable();
				}
			}
	}
			private class EventHandlerDelete implements ActionListener
			{
				public void actionPerformed (ActionEvent myEvent) 
				{
					if (myEvent.getSource() == btnDeleteRow){
						PoistaLevy();
						populateTable();
					}
				}
		}
		private void getNewRecordFromUser(){
			JTextField nimiField = new JTextField(10);
		    JTextField artistiField = new JTextField(10);
		    JTextField julkaisvuosiField = new JTextField(10); // Code added here
	 
		    JPanel myPanel = new JPanel();
		    
		    myPanel.add(new JLabel("Nimi:"));
		    myPanel.add(nimiField);
		    
		    myPanel.add(new JLabel("Artisti:"));
		    myPanel.add(artistiField);
		    
		    myPanel.add(new JLabel("Julkaisuvuosi:")); 
		    myPanel.add(julkaisvuosiField); 
		    
		    int result = JOptionPane.showConfirmDialog(null, myPanel, "Syˆt‰ levyn tiedot", JOptionPane.OK_CANCEL_OPTION);
		    
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	try {
		    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost, 127.0.0.1");
		   //         Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268747", "sql7268747", "bkl334CCZ");
	 
	            Statement stmt = con.createStatement();
	          	ResultSet rs = stmt.executeQuery("SELECT * FROM Levy");
	         
	            PreparedStatement ps = con.prepareStatement("INSERT INTO LEVY (nimi, artisti, julkaisuvuosi) VALUES (?, ?, ?)");
	            ps.setString(1, nimiField.getText());
	            ps.setString(2, artistiField.getText());
	            ps.setString(3, julkaisvuosiField.getText());
	            ps.executeUpdate();
	            
		    	con.close();
		    	
		    	} catch (Exception e) {
		            System.out.println(e);
		        }
		    		
		    }}
		
		    private void PoistaLevy () {
 
		        int SelectedRow = tableRecord.getSelectedRow();
	            String dnimi = (String) tableRecord.getValueAt(SelectedRow, 0);
	            String SQL = "DELETE FROM LEVY WHERE nimi = '" + dnimi +"'";
	            
	            Object[] options = { "Poista levy", "Peruuta" };

	
	            try {
	            	Connection con = DriverManager.getConnection("jdbc:mysql://localhost, 127.0.0.1");
		           // Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7268747", "sql7268747", "bkl334CCZ");
		        Statement stmt = con.createStatement();
				stmt.executeUpdate(SQL);
				con.close();
				dbItems--;
	            }
		        catch (Exception e) {
		            System.out.println(e);
		        }
			}
		
		
		public static void main(String[] args) {
			Levyhylly frame = new Levyhylly();
			frame.setVisible(true);
			
		}
	}