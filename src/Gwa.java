import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gwa extends JFrame {
	
	private Image img_logo = new ImageIcon(Gwa.class.getResource("res/PUP.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gwa frame = new Gwa();
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
	
	private JPanel contentPane;
	private JTextField txtStudID;
	private JTextField txtGwa;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable table;
	private final JScrollPane scrollPane = new JScrollPane();
	
	public void Student() {
		showTableData();
		Student();
	}
	
	public void showTableData() {
		try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
				String sql = "SELECT * FROM student";
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData(); 
		
				int q = stData.getColumnCount();
				
				DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
			RecordTable.setRowCount(0);
			
			while(rs.next()) {
				Vector columnData = new Vector();
				
				for(int i = 1; i<=q; i++) {
					columnData.add(rs.getString("studentID"));
					columnData.add(rs.getString("firstName"));
					columnData.add(rs.getString("lastName"));
					columnData.add(rs.getString("middleInitial"));
					columnData.add(rs.getString("suffix"));
					columnData.add(rs.getString("program"));
					columnData.add(rs.getString("department"));
					columnData.add(rs.getString("yearLevel"));
					columnData.add(rs.getString("section"));
					columnData.add(rs.getString("gwa"));
					
				}
				RecordTable.addRow(columnData);
			}
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
	}
	
	public Gwa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 670);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(220, 220, 220), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 72, 1200, 588);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBounds(10, 10, 1180, 55);
		panel.add(panel_1);
		
		JLabel lblInputGwa = new JLabel("GENERAL WEIGHTED AVERAGE");
		lblInputGwa.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputGwa.setForeground(Color.WHITE);
		lblInputGwa.setFont(new Font("Arial", Font.BOLD, 50));
		lblInputGwa.setBounds(0, 0, 1180, 55);
		panel_1.add(lblInputGwa);
		
		JPanel paneSearch = new JPanel();
		paneSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneSearch.setBounds(10, 75, 511, 55);
		panel.add(paneSearch);
		paneSearch.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Arial", Font.PLAIN, 22));
		lblStudentId.setBounds(10, 14, 115, 25);
		paneSearch.add(lblStudentId);
		
		txtStudID = new JTextField();
		txtStudID.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					String sql = "SELECT firstName, lastName, middleInitial, suffix, program, department, yearLevel, section = ?";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtStudID.getText());
					
					ResultSet rs = pst.executeQuery();
					if(rs.next()==false) {
						JOptionPane.showMessageDialog(null, "Record not found!");
					}else{
						String gwa = rs.getString(1);
						
						txtGwa.setText(gwa);
						txtStudID.requestFocus();
						showTableData();
						}
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
			}
		});
		txtStudID.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtStudID.setColumns(10);
		txtStudID.setBounds(123, 14, 228, 28);
		paneSearch.add(txtStudID);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "SELECT gwa = ?";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtStudID.getText());
					
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==false) {
						JOptionPane.showMessageDialog(null, "Record not found!");
						
						txtGwa.setText("");
						txtStudID.requestFocus();
					}else{
						String gwa = rs.getString(1);
						
						txtGwa.setText(gwa);
						txtStudID.requestFocus();
						showTableData();
					}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 25));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setBounds(360, 11, 139, 35);
		paneSearch.add(btnSearch);
		
		JPanel paneGwa = new JPanel();
		paneGwa.setLayout(null);
		paneGwa.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Input GWA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		paneGwa.setBounds(10, 363, 207, 55);
		panel.add(paneGwa);
		
		JLabel lblGwa = new JLabel("GWA:");
		lblGwa.setFont(new Font("Arial", Font.PLAIN, 22));
		lblGwa.setBounds(10, 11, 69, 34);
		paneGwa.add(lblGwa);
		
		txtGwa = new JTextField();
		txtGwa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtGwa.setColumns(10);
		txtGwa.setBounds(75, 14, 122, 28);
		paneGwa.add(txtGwa);
		
		JButton btnCreate = new JButton("Save");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "INSERT INTO student"
							+ "(gwa)"
							+ "VALUES (?)";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtGwa.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record inserted successfully!");
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
					txtGwa.setText("");
					txtStudID.requestFocus();
					showTableData();
			}
		});
		btnCreate.setBounds(227, 376, 139, 35);
		panel.add(btnCreate);
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Arial", Font.BOLD, 25));
		btnCreate.setBackground(new Color(128, 0, 0));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(382, 376, 139, 35);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "UPDATE student SET gwa = ? WHERE studentID = ?";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtGwa.getText());
					JOptionPane.showMessageDialog(null, "Record updated successfully!");
					}catch (SQLException e1) {
					e1.printStackTrace();
					}
					txtGwa.setText("");
					txtStudID.requestFocus();
					showTableData();
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 25));
		btnUpdate.setBackground(new Color(128, 0, 0));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "DELETE FROM student WHERE studentID = ?";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank","root","");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtStudID.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted successfully!");
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				txtGwa.setText("");
				txtStudID.requestFocus();
				showTableData();
			}
		});
		btnDelete.setBounds(540, 376, 139, 35);
		panel.add(btnDelete);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 25));
		btnDelete.setBackground(new Color(128, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 140, 1180, 213);
		panel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setEnabled(false);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
				new Object [][] {
				},
				new String[] {
						"Student ID", "First Name", "Last Name", "MI.", "Suffix", "Program", "Department", "Year Level", "Section","GWA" 
				}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		});
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 0, 70, 70);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		contentPane.add(lblIconLogo);
		
		JPanel paneHome = new JPanel();
		paneHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu frameMenu = new Menu();
				frameMenu.setVisible(true);
				dispose();
			}
		});
		paneHome.setBounds(90, 10, 96, 45);
		contentPane.add(paneHome);
		paneHome.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Home");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 96, 45);
		paneHome.add(lblNewLabel);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
