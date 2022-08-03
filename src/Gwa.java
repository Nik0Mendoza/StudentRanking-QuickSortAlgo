import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gwa extends JFrame {

	private Image img_logo = new ImageIcon(Gwa.class.getResource("res/PUP.png")).getImage().getScaledInstance(65, 65,
			Image.SCALE_SMOOTH);

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
	private JTextField txtYearLevel;
	private JTextField txtSem;

	Connection con = null;
	PreparedStatement pst = null;
	PreparedStatement pst2 = null;
	PreparedStatement pst3 = null;
	PreparedStatement pst4 = null;
	PreparedStatement pst5 = null;
	ResultSet rs = null;
	private JTable table;
	private final JScrollPane scrollPane = new JScrollPane();

	public void Gwa() {
		showTableData();
		Gwa();
	}

	public void showTableData() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", ""); // database name
			String sql = "SELECT student.studentID, program.programDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, semester.semDesc, grade.gwa, lister.listerDesc FROM student, program, year, semester, grade, lister WHERE student.studentID = grade.studentID AND student.programID = program.programID AND student.yearID = year.yearID AND grade.semID = semester.semID AND grade.listerID = lister.listerID;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();

			int q = stData.getColumnCount();

			DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
			RecordTable.setRowCount(0);

			while (rs.next()) {
				Vector<String> columnData = new Vector<String>();

				for (int i = 1; i <= q; i++) {
					columnData.add(rs.getString("studentID"));
					columnData.add(rs.getString("programDesc"));
					columnData.add(rs.getString("lastName"));
					columnData.add(rs.getString("firstName"));
					columnData.add(rs.getString("middleInitial"));
					columnData.add(rs.getString("suffix"));
					columnData.add(rs.getString("yearDesc"));
					columnData.add(rs.getString("semDesc"));
					columnData.add(rs.getString("gwa"));
					columnData.add(rs.getString("listerDesc"));
				}
				RecordTable.addRow(columnData);
			}
		} catch (Exception ex) {
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

		JLabel lblInputGwa = new JLabel("GRADES");
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					showTableData();
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
				showTableData();
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 25));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setBounds(360, 11, 139, 35);
		paneSearch.add(btnSearch);

		JPanel paneGwa = new JPanel();
		paneGwa.setLayout(null);
		paneGwa.setBorder(null);
		paneGwa.setBounds(10, 363, 263, 132);
		panel.add(paneGwa);

		JLabel lblGwa = new JLabel("GWA:");
		lblGwa.setFont(new Font("Arial", Font.PLAIN, 22));
		lblGwa.setBounds(10, 88, 69, 34);
		paneGwa.add(lblGwa);

		txtGwa = new JTextField();
		txtGwa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtGwa.setColumns(10);
		txtGwa.setBounds(121, 90, 122, 28);
		paneGwa.add(txtGwa);

		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setFont(new Font("Arial", Font.PLAIN, 22));
		lblSemester.setBounds(10, 50, 102, 34);
		paneGwa.add(lblSemester);

		txtYearLevel = new JTextField();
		txtYearLevel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtYearLevel.setColumns(10);
		txtYearLevel.setBounds(121, 12, 122, 28);
		paneGwa.add(txtYearLevel);

		JLabel lblYearLevel = new JLabel("Year Level:");
		lblYearLevel.setFont(new Font("Arial", Font.PLAIN, 22));
		lblYearLevel.setBounds(10, 10, 122, 34);
		paneGwa.add(lblYearLevel);

		txtSem = new JTextField();
		txtSem.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtSem.setColumns(10);
		txtSem.setBounds(121, 50, 122, 28);
		paneGwa.add(txtSem);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "INSERT INTO grade (studentID, yearID, semID, gwa, listerID) VALUES (?, ?, ?, ?, ?);";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);

					String sql2 = "SELECT yearDesc, yearID FROM year;";
					pst2 = con.prepareStatement(sql2);
					ResultSet rs2 = pst2.executeQuery();
					String year = txtYearLevel.getText();
					int year2=0;
					while (rs2.next()) {
						if (year.equals(rs2.getString(1))) {
							year2 = rs2.getInt(2);
							break;
						}
					}

					String sql3 = "SELECT semDesc, semID  FROM semester;";
					pst3 = con.prepareStatement(sql3);
					ResultSet rs3 = pst3.executeQuery();
					String semester = txtSem.getText();
					int semester2 = 0;
					while (rs3.next()) {
						if (semester.equals(rs3.getString(1))) {
							semester2 = rs3.getInt(2);
							break;
						}
					}

					String sql4 = "SELECT gwa FROM GRADE WHERE studentID = ?;";
					pst4 = con.prepareStatement(sql4);
					pst4.setString(1, txtStudID.getText());
					ResultSet rs4 = pst4.executeQuery();
					double gwa = Double.parseDouble(txtGwa.getText());
					int listerID = 0;
					while (rs4.next()){
						if (gwa >= 1 && gwa <= 1.50) {
							listerID = 2;
						} else if (gwa >= 1.51 && gwa <= 1.75) {
							listerID = 3;
						} else {
							listerID = 1;
						}
						break;
					}

					pst.setString(1, txtStudID.getText());
					pst.setInt(2, year2);
					pst.setInt(3, semester2);
					pst.setDouble(4, gwa);
					pst.setInt(5, listerID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record inserted successfully!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				txtYearLevel.setText("");
				txtSem.setText("");
				txtGwa.setText("");
				txtStudID.requestFocus();
				showTableData();
			}
		});
		btnInsert.setBounds(283, 392, 139, 35);
		panel.add(btnInsert);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setFont(new Font("Arial", Font.BOLD, 25));
		btnInsert.setBackground(new Color(128, 0, 0));

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(283, 437, 139, 35);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "UPDATE grade SET gwa = ?, listerID =? WHERE studentID = ? AND yearID = ? AND semID = ?;";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);

					String sql2 = "SELECT yearDesc, yearID FROM year;";
					pst2 = con.prepareStatement(sql2);
					ResultSet rs2 = pst2.executeQuery();
					String year = txtYearLevel.getText();
					int year2=0;
					while (rs2.next()) {
						if (year.equals(rs2.getString(1))) {
							year2 = rs2.getInt(2);
							break;
						}
					}

					String sql3 = "SELECT semDesc, semID  FROM semester;";
					pst3 = con.prepareStatement(sql3);
					ResultSet rs3 = pst3.executeQuery();
					String semester = txtSem.getText();
					int semester2 = 0;
					while (rs3.next()) {
						if (semester.equals(rs3.getString(1))) {
							semester2 = rs3.getInt(2);
							break;
						}
					}

					String sql4 = "SELECT gwa FROM GRADE WHERE studentID = ?;";
					pst4 = con.prepareStatement(sql4);
					pst4.setString(1, txtStudID.getText());
					ResultSet rs4 = pst4.executeQuery();
					double gwa = Double.parseDouble(txtGwa.getText());
					int listerID = 0;
					while (rs4.next()){
						if (gwa >= 1 && gwa <= 1.50) {
							listerID = 2;
						} else if (gwa >= 1.51 && gwa <= 1.75) {
							listerID = 3;
						} else {
							listerID = 1;
						}
						break;
					}
					
					pst.setDouble(1, gwa);
					pst.setInt(2, listerID);
					pst.setString(3, txtStudID.getText());
					pst.setInt(4, year2);
					pst.setInt(5, semester2);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated successfully!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				txtYearLevel.setText("");
				txtSem.setText("");
				txtGwa.setText("");
				txtStudID.requestFocus();
				showTableData();
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 25));
		btnUpdate.setBackground(new Color(128, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 140, 1180, 213);
		panel.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setEnabled(false);
		table.setFont(new Font("Arial", Font.PLAIN, 14)); // Updated UI
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Student ID", "Program", "Last Name", "First Name", "MI", "Suffix", "Year Level", "Semester",
						"GWA", "Lister"
				}) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class
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
