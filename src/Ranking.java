import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking extends JFrame {

	static Connection con = null;
	static PreparedStatement pst = null;
	static PreparedStatement pst2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;

	private static JTable table;

	private Image img_logo = new ImageIcon(Ranking.class.getResource("res/PUP.png")).getImage().getScaledInstance(65,
			65, Image.SCALE_SMOOTH);

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Ranking() {
		showTableData();
		Ranking();
	}

	public static ArrayList listofStudents() throws SQLException {
		String sql = "SELECT student.studentID, program.programDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, section.sectionDesc, grade.gwa FROM student, program, year, section, grade WHERE student.programID = program.programID AND student.yearID = year.yearID AND student.sectionID = section.sectionID AND student.studentID = grade.studentID;";
		con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
		pst = con.prepareStatement(sql);

		ResultSet rs = pst.executeQuery();

		String sql2 = "SELECT COUNT(*) AS recordCount FROM grade;";
		pst2 = con.prepareStatement(sql2);
		ResultSet rs2 = pst2.executeQuery();

		ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();

		int q = stData.getColumnCount();

		rs2.next();
		int count = rs2.getInt("recordCount");

		StudentGrade[] rankings = new StudentGrade[count];

		ArrayList<StudentGrade> rankingList = new ArrayList<>();

		while (rs.next()) {
			StudentGrade ranking = new StudentGrade(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9));
			rankingList.add(ranking);
		}

		Collections.sort(rankingList, new Comparator<StudentGrade>() {
			public int compare(StudentGrade sg1, StudentGrade sg2) {
				return Double.valueOf(sg1.gwa).compareTo(sg2.gwa);
			}
		});
		return rankingList;
	}

	public void showTableData() {
		try {

			DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
			RecordTable.setRowCount(0);

			ArrayList<StudentGrade> list = listofStudents();
			Object rowData[] = new Object[9];
			for (int i = 0; i < list.size(); i++) {
				rowData[0] = list.get(i).studentID;
				rowData[1] = list.get(i).programDesc;
				rowData[2] = list.get(i).lastName;
				rowData[3] = list.get(i).firstName;
				rowData[4] = list.get(i).middleInitial;
				rowData[5] = list.get(i).suffix;
				rowData[6] = list.get(i).yearDesc;
				rowData[7] = list.get(i).sectionDesc;
				rowData[8] = list.get(i).gwa;
				RecordTable.addRow(rowData);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	/**
	 * Create the frame.
	 */
	public Ranking() {
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
		panel_1.setBounds(10, 11, 1180, 55);
		panel.add(panel_1);

		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setForeground(Color.WHITE);
		lblRanking.setFont(new Font("Arial", Font.BOLD, 50));
		lblRanking.setBounds(0, 0, 1180, 55);
		panel_1.add(lblRanking);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 76, 1180, 458);
		panel.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportView(scrollPane);

		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 0, 70, 70);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		contentPane.add(lblIconLogo);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setEnabled(false);
		table.setFont(new Font("Arial", Font.PLAIN, 14)); // Updated UI
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Student ID", "Program", "Last Name", "First Name", "MI", "Suffix", "Year Level", "Section",
						"GWA"
				}) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, Double.class
			};

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					table.print();
				} catch (java.awt.print.PrinterException e1) {
					System.err.format("No Printer Found!", e1.getMessage());
				}
			}
		});
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Arial", Font.BOLD, 30));
		btnPrint.setBackground(new Color(128, 0, 0));
		btnPrint.setBounds(540, 541, 139, 40);
		panel.add(btnPrint);

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
