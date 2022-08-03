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
import javax.swing.border.EtchedBorder;
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
import javax.swing.ScrollPaneConstants;

public class Student extends JFrame {

	private Image img_logo = new ImageIcon(Student.class.getResource("res/PUP.png")).getImage().getScaledInstance(65,
			65, Image.SCALE_SMOOTH);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JPanel paneHome = new JPanel();
	private final JLabel lblHome = new JLabel("Home");
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("STUDENT");
	private final JPanel paneInput = new JPanel();
	private final JPanel panelSearch = new JPanel();
	private final JLabel lblStudentId = new JLabel("Student ID:");
	private final JPanel panel_1_1 = new JPanel();
	private final JLabel lblStudentId_1 = new JLabel("Student ID:");
	private final JLabel lblStudentName = new JLabel("First Name:");
	private final JTextField txtStudIDReg = new JTextField();
	private final JTextField txtFN = new JTextField();
	private final JButton btnCreate = new JButton("Create");
	private final JButton btnDelete = new JButton("Delete");
	private final JButton btnUpdate = new JButton("Update");
	private final JLabel lblLastName = new JLabel("Last Name:");
	private JTextField txtLN;
	private JTextField txtMI;
	private JTextField txtSX;
	private JTextField txtStudID;
	private JTextField txtProg;
	private JTextField txtDept;
	private JTextField txtYL;
	private JTextField txtSec;

	Connection con = null;
	PreparedStatement pst = null;
	PreparedStatement pst2 = null;
	PreparedStatement pst3 = null;
	PreparedStatement pst4 = null;
	PreparedStatement pst5 = null;
	ResultSet rs = null;
	private JTable table;
	private final JScrollPane scrollPane = new JScrollPane();

	public void Student() {
		showTableData();
		Student();
	}

	public void showTableData() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", ""); // database name
			String sql = "SELECT student.studentID, program.programDesc, department.deptDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, section.sectionDesc FROM student, program, department, year, section WHERE student.programID = program.programID AND student.deptID = department.deptID AND student.yearID = year.yearID AND student.sectionID = section.sectionID;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();

			int q = stData.getColumnCount();

			DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
			RecordTable.setRowCount(0);

			while (rs.next()) {
				Vector columnData = new Vector();

				for (int i = 1; i <= q; i++) {
					columnData.add(rs.getString("studentID"));
					columnData.add(rs.getString("programDesc"));
					columnData.add(rs.getString("deptDesc"));
					columnData.add(rs.getString("lastName"));
					columnData.add(rs.getString("firstName"));
					columnData.add(rs.getString("middleInitial"));
					columnData.add(rs.getString("suffix"));
					columnData.add(rs.getString("yearDesc"));
					columnData.add(rs.getString("sectionDesc"));
				}
				RecordTable.addRow(columnData);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	private void clearFields() {
		txtStudIDReg.setText(null);
		txtFN.setText(null);
		txtMI.setText(null);
		txtLN.setText(null);
		txtSX.setText(null);
		txtProg.setText(null);
		txtDept.setText(null);
		txtYL.setText(null);
		txtSec.setText(null);
		DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
        RecordTable.setRowCount(0);
	}
	
	/**
	 * Create the frame.
	 */
	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 670);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(220, 220, 220), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(10, 72, 1200, 588);
		contentPane.add(panel);
		panel.setLayout(null);
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBounds(10, 10, 1180, 55);

		panel.add(panel_1);
		panel_1.setLayout(null);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 0, 1180, 55);

		panel_1.add(lblNewLabel);
		paneInput.setLayout(null);
		paneInput.setBorder(new LineBorder(new Color(48, 71, 94)));
		paneInput.setBounds(10, 75, 1180, 503);

		panel.add(paneInput);
		panelSearch.setLayout(null);
		panelSearch.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSearch.setBounds(10, 5, 510, 55);

		paneInput.add(panelSearch);
		lblStudentId.setFont(new Font("Arial", Font.PLAIN, 22));
		lblStudentId.setBounds(10, 14, 114, 31);

		panelSearch.add(lblStudentId);

		txtStudID = new JTextField();
		txtStudID.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String sql = "SELECT student.studentID, program.programDesc, department.deptDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, section.sectionDesc FROM student, program, department, year, section WHERE student.programID = program.programID AND student.deptID = department.deptID AND student.yearID = year.yearID AND student.sectionID = section.sectionID AND student.studentID = ?;";

						con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
						pst = con.prepareStatement(sql);
						pst.setString(1, txtStudID.getText());

						ResultSet rs = pst.executeQuery();

						if (rs.next() == false) {
							JOptionPane.showMessageDialog(null, "Record not found!");
						} else {
							String studentID = rs.getString(1);
							String program = rs.getString(2);
							String department = rs.getString(3);
							String lastName = rs.getString(4);
							String firstName = rs.getString(5);
							String middleInitial = rs.getString(6);
							String suffix = rs.getString(7);
							String yearLevel = rs.getString(8);
							String section = rs.getString(9);

							txtStudIDReg.setText(studentID);
							txtFN.setText(firstName.trim());
							txtLN.setText(lastName.trim());
							txtMI.setText(middleInitial);
							txtSX.setText(suffix);
							txtProg.setText(program.trim());
							txtDept.setText(department);
							txtYL.setText(yearLevel);
							txtSec.setText(section);
							txtStudID.requestFocus();
							showTableData();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtStudID.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtStudID.setColumns(10);
		txtStudID.setBounds(123, 14, 228, 28);
		panelSearch.add(txtStudID);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "SELECT student.studentID, program.programDesc, department.deptDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, section.sectionDesc FROM student, program, department, year, section WHERE student.programID = program.programID AND student.deptID = department.deptID AND student.yearID = year.yearID AND student.sectionID = section.sectionID AND student.studentID = ?;";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtStudID.getText());

					ResultSet rs = pst.executeQuery();

					if (rs.next() == false) {
						JOptionPane.showMessageDialog(null, "Record not found!");
					} else {
						String studentID = rs.getString(1);
						String program = rs.getString(2);
						String department = rs.getString(3);
						String lastName = rs.getString(4);
						String firstName = rs.getString(5);
						String middleInitial = rs.getString(6);
						String suffix = rs.getString(7);
						String yearLevel = rs.getString(8);
						String section = rs.getString(9);

						txtStudIDReg.setText(studentID);
						txtFN.setText(firstName.trim());
						txtLN.setText(lastName.trim());
						txtMI.setText(middleInitial);
						txtSX.setText(suffix);
						txtProg.setText(program.trim());
						txtDept.setText(department);
						txtYL.setText(yearLevel);
						txtSec.setText(section);
						txtStudID.requestFocus();
						showTableData();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 25));
		btnSearch.setBackground(new Color(128, 0, 0));
		btnSearch.setBounds(361, 11, 139, 35);
		panelSearch.add(btnSearch);
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Register Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(10, 70, 1160, 134);

		paneInput.add(panel_1_1);
		lblStudentId_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblStudentId_1.setBounds(10, 16, 115, 31);

		panel_1_1.add(lblStudentId_1);
		lblStudentName.setFont(new Font("Arial", Font.PLAIN, 23));
		lblStudentName.setBounds(10, 57, 122, 25);

		panel_1_1.add(lblStudentName);
		txtStudIDReg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStudIDReg.setColumns(10);
		txtStudIDReg.setBounds(124, 14, 225, 29);

		panel_1_1.add(txtStudIDReg);
		txtFN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFN.setColumns(10);
		txtFN.setBounds(134, 54, 243, 29);

		panel_1_1.add(txtFN);
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 23));
		lblLastName.setBounds(387, 54, 125, 31);

		panel_1_1.add(lblLastName);

		JLabel lblMiddleName = new JLabel("Middle Initial:");
		lblMiddleName.setFont(new Font("Arial", Font.PLAIN, 22));
		lblMiddleName.setBounds(710, 55, 132, 31);
		panel_1_1.add(lblMiddleName);

		txtLN = new JTextField();
		txtLN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLN.setColumns(10);
		txtLN.setBounds(511, 55, 189, 29);
		panel_1_1.add(txtLN);

		txtMI = new JTextField();
		txtMI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMI.setColumns(10);
		txtMI.setBounds(840, 55, 50, 29);
		panel_1_1.add(txtMI);

		JLabel lblSuffix = new JLabel("Suffix:");
		lblSuffix.setFont(new Font("Arial", Font.PLAIN, 22));
		lblSuffix.setBounds(898, 57, 67, 26);
		panel_1_1.add(lblSuffix);

		txtSX = new JTextField();
		txtSX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSX.setColumns(10);
		txtSX.setBounds(964, 55, 56, 29);
		panel_1_1.add(txtSX);

		JLabel lblProgram = new JLabel("Program:");
		lblProgram.setFont(new Font("Arial", Font.PLAIN, 23));
		lblProgram.setBounds(10, 92, 115, 25);
		panel_1_1.add(lblProgram);

		txtProg = new JTextField();
		txtProg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtProg.setColumns(10);
		txtProg.setBounds(109, 92, 279, 29);
		panel_1_1.add(txtProg);

		JLabel lblDepartmrnt = new JLabel("Department:");
		lblDepartmrnt.setFont(new Font("Arial", Font.PLAIN, 22));
		lblDepartmrnt.setBounds(397, 90, 132, 31);
		panel_1_1.add(lblDepartmrnt);

		txtDept = new JTextField();
		txtDept.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDept.setColumns(10);
		txtDept.setBounds(521, 92, 179, 29);
		panel_1_1.add(txtDept);

		JLabel lblYearLevel = new JLabel("Year Level:");
		lblYearLevel.setFont(new Font("Arial", Font.PLAIN, 22));
		lblYearLevel.setBounds(710, 93, 115, 25);
		panel_1_1.add(lblYearLevel);

		txtYL = new JTextField();
		txtYL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtYL.setColumns(10);
		txtYL.setBounds(821, 92, 56, 29);
		panel_1_1.add(txtYL);

		JLabel lblSection = new JLabel("Section:");
		lblSection.setFont(new Font("Arial", Font.PLAIN, 22));
		lblSection.setBounds(883, 94, 82, 25);
		panel_1_1.add(lblSection);

		txtSec = new JTextField();
		txtSec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSec.setColumns(10);
		txtSec.setBounds(964, 93, 56, 29);
		panel_1_1.add(txtSec);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "INSERT INTO student (studentID, programID, deptID, yearID, sectionID, firstName, lastName, middleInitial, suffix) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);
					
					String selectSql = "SELECT * FROM student where studentID = '"+txtStudIDReg+"'";
					rs = pst.executeQuery(selectSql);

				if(rs.next()) {
					String sql2 = "SELECT programDesc, programID FROM program;";
					pst2 = con.prepareStatement(sql2);
					ResultSet rs2 = pst2.executeQuery();
					String program = txtProg.getText();
					while (rs2.next()) {
						if (program.equals(rs2.getString(1))) {
							program = rs2.getString(2);
							break;
						}
						break;
					}

					String sql3 = "SELECT deptID, deptDesc FROM department;";
					pst3 = con.prepareStatement(sql3);
					ResultSet rs3 = pst3.executeQuery();
					String department = txtDept.getText();
					while (rs3.next()) {
						if (department.equals(rs3.getString(2))) {
							department = rs3.getString(1);
							break;
						}
						break;
					}

					String sql4 = "SELECT yearID, yearDesc FROM year;";
					pst4 = con.prepareStatement(sql4);
					ResultSet rs4 = pst4.executeQuery();
					String yearLevel = txtYL.getText();
					while (rs4.next()) {
						if (yearLevel.equals(rs4.getString(2))) {
							yearLevel = rs4.getString(1);
							break;
						}
						break;
					}

					String sql5 = "SELECT yearID, yearDesc FROM year;";
					pst5 = con.prepareStatement(sql5);
					ResultSet rs5 = pst5.executeQuery();
					String section = txtSec.getText();
					while (rs5.next()) {
						if (section.equals(rs5.getString(2))) {
							section = rs5.getString(1);
							break;
						}
						break;
					}
					

					pst.setString(1, txtStudIDReg.getText());
					pst.setString(2, program);
					pst.setString(3, department);
					pst.setString(4, yearLevel);
					pst.setString(5, section);
					pst.setString(6, txtFN.getText());
					pst.setString(7, txtLN.getText());
					pst.setString(8, txtMI.getText());
					pst.setString(9, txtSX.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record inserted successfully!");
				
				txtStudIDReg.setText("");
				txtFN.setText("");
				txtLN.setText("");
				txtMI.setText("");
				txtSX.setText("");
				txtProg.setText("");
				txtDept.setText("");
				txtYL.setText("");
				txtSec.setText("");
				txtStudID.requestFocus();
				showTableData();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Student ID already exists");
				clearFields(); //clear text fields
			}
			}catch (SQLException e1) {
				e1.printStackTrace();
		}
	}
		});
		btnCreate.setBounds(1035, 16, 115, 31);
		panel_1_1.add(btnCreate);
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Arial", Font.BOLD, 21));
		btnCreate.setBackground(new Color(128, 0, 0));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "UPDATE student SET programID = ?, deptID = ?, yearID = ?, sectionID = ?, firstName = ?, lastName = ?, middleInitial = ?, suffix = ? WHERE studentID = ?";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);

					String sql2 = "SELECT programDesc, programID FROM program;";
					pst2 = con.prepareStatement(sql2);
					ResultSet rs2 = pst2.executeQuery();
					String program = txtProg.getText();
					while (rs2.next()) {
						if (program.equals(rs2.getString(1))) {
							program = rs2.getString(2);
							break;
						}
						break;
					}

					String sql3 = "SELECT deptID, deptDesc FROM department;";
					pst3 = con.prepareStatement(sql3);
					ResultSet rs3 = pst3.executeQuery();
					String department = txtDept.getText();
					while (rs3.next()) {
						if (department.equals(rs3.getString(2))) {
							department = rs3.getString(1);
							break;
						}
						break;
					}

					String sql4 = "SELECT yearID, yearDesc FROM year;";
					pst4 = con.prepareStatement(sql4);
					ResultSet rs4 = pst4.executeQuery();
					String yearLevel = txtYL.getText();
					while (rs4.next()) {
						if (yearLevel.equals(rs4.getString(2))) {
							yearLevel = rs4.getString(1);
							break;
						}
						break;
					}

					String sql5 = "SELECT yearID, yearDesc FROM year;";
					pst5 = con.prepareStatement(sql5);
					ResultSet rs5 = pst5.executeQuery();
					String section = txtSec.getText();
					while (rs5.next()) {
						if (section.equals(rs5.getString(2))) {
							section = rs5.getString(1);
							break;
						}
						break;
					}

					pst.setString(9, txtStudIDReg.getText());
					pst.setString(1, program);
					pst.setString(2, department);
					pst.setString(3, yearLevel);
					pst.setString(4, section);
					pst.setString(5, txtFN.getText());
					pst.setString(6, txtLN.getText());
					pst.setString(7, txtMI.getText());
					pst.setString(8, txtSX.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated successfully!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				txtStudID.requestFocus();
				showTableData();
			}
		});
		btnUpdate.setBounds(1035, 53, 115, 32);
		panel_1_1.add(btnUpdate);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 21));
		btnUpdate.setBackground(new Color(128, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "DELETE FROM student WHERE studentID = ?";

					con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
					pst = con.prepareStatement(sql);
					pst.setString(1, txtStudID.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted successfully!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				txtStudIDReg.setText("");
				txtFN.setText("");
				txtLN.setText("");
				txtMI.setText("");
				txtSX.setText("");
				txtProg.setText("");
				txtDept.setText("");
				txtYL.setText("");
				txtSec.setText("");
				txtStudID.requestFocus();
				showTableData();
			}
		});
		btnDelete.setBounds(1035, 92, 115, 31);
		panel_1_1.add(btnDelete);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 21));
		btnDelete.setBackground(new Color(128, 0, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 214, 1160, 279);

		paneInput.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Student ID", "Program", "Department", "Last Name", "First Name", "MI", "Suffix", "Year Level", "Section"
				}) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class
			};

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 0, 70, 70);
		contentPane.add(lblIconLogo);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		paneHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu frameMenu = new Menu();
				frameMenu.setVisible(true);
				dispose();
			}
		});
		paneHome.setBorder(null);
		paneHome.setBounds(90, 10, 96, 45);

		contentPane.add(paneHome);
		paneHome.setLayout(null);
		lblHome.setFont(new Font("Arial", Font.PLAIN, 28));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(0, 0, 96, 45);

		paneHome.add(lblHome);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}