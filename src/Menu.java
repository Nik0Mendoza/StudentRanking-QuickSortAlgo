import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JFrame {
	
	private Image img_logoPuP = new ImageIcon(Login.class.getResource("res/PUP.png")).getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
	private Image img_menu = new ImageIcon(Login.class.getResource("res/MENU.png")).getImage().getScaledInstance(970, 572, Image.SCALE_SMOOTH);
	private Image img_student = new ImageIcon(Login.class.getResource("res/STUDENT.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private Image img_gwa = new ImageIcon(Login.class.getResource("res/GRADES.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private Image img_rank = new ImageIcon(Login.class.getResource("res/RANKING.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private Image img_report = new ImageIcon(Login.class.getResource("res/REPORT.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private Image img_out = new ImageIcon(Login.class.getResource("res/LOGOUT.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 670);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(2, 2, 247, 668);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(10, 16, 227, 217);
		lblIcon.setIcon(new ImageIcon(img_logoPuP));
		panel.add(lblIcon);
		
		JLabel lblGreet = new JLabel("Hi, PUPian!");
		lblGreet.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreet.setFont(new Font("Arial", Font.PLAIN, 30));
		lblGreet.setBounds(10, 228, 227, 51);
		panel.add(lblGreet);
		
		JPanel paneStudent = new JPanel();
		paneStudent.addMouseListener(new PanelButtonMouseAdapter(paneStudent){
			@Override
			public void mouseClicked(MouseEvent e) {
				Student frameStudent = new Student();
				frameStudent.setVisible(true);
				frameStudent.showTableData();
				dispose();
			}
		});
		paneStudent.setBorder(null);
		paneStudent.setBackground(new Color(211, 211, 211));
		paneStudent.setBounds(0, 289, 247, 72);
		panel.add(paneStudent);
		paneStudent.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(83, 10, 154, 52);
		paneStudent.add(lblNewLabel);
		
		JLabel lblIconStudent = new JLabel("");
		lblIconStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconStudent.setBounds(10, 0, 71, 72);
		lblIconStudent.setIcon(new ImageIcon(img_student));
		paneStudent.add(lblIconStudent);
		
		JPanel paneGrades = new JPanel();
		paneGrades.addMouseListener(new PanelButtonMouseAdapter(paneGrades){
			@Override
			public void mouseClicked(MouseEvent e) {
				Gwa frameGwa = new Gwa();
				frameGwa.setVisible(true);
				frameGwa.showTableData();
				dispose();
			}
		});
		paneGrades.setBorder(null);
		paneGrades.setBackground(new Color(211, 211, 211));
		paneGrades.setBounds(0, 360, 247, 72);
		panel.add(paneGrades);
		paneGrades.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Grades");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(80, 10, 157, 52);
		paneGrades.add(lblNewLabel_1);
		
		JLabel lblIconGWA = new JLabel("");
		lblIconGWA.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconGWA.setBounds(10, 0, 71, 72);
		lblIconGWA.setIcon(new ImageIcon(img_gwa));
		paneGrades.add(lblIconGWA);
		
		JPanel paneRanking = new JPanel();
		paneRanking.addMouseListener(new PanelButtonMouseAdapter(paneRanking){
			@Override
			public void mouseClicked(MouseEvent e) {
				Ranking frameRanking = new Ranking();
				frameRanking.setVisible(true);
				frameRanking.showTableData();
				dispose();
			}
		});
		paneRanking.setBorder(null);
		paneRanking.setBackground(new Color(211, 211, 211));
		paneRanking.setBounds(0, 431, 247, 72);
		panel.add(paneRanking);
		paneRanking.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Rankings");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_2.setBounds(81, 10, 156, 51);
		paneRanking.add(lblNewLabel_2);
		
		JLabel lblIconRank = new JLabel("");
		lblIconRank.setBounds(10, 0, 61, 72);
		lblIconRank.setIcon(new ImageIcon(img_rank));
		paneRanking.add(lblIconRank);
		
		JPanel paneReport = new JPanel();
		paneReport.addMouseListener(new PanelButtonMouseAdapter(paneReport){
			@Override
			public void mouseClicked(MouseEvent e) {
				Report frameReport = new Report();
				frameReport.setVisible(true);
				dispose();
			}
		});
		paneReport.setBorder(null);
		paneReport.setBackground(new Color(211, 211, 211));
		paneReport.setBounds(0, 502, 247, 72);
		panel.add(paneReport);
		paneReport.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Report");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_3.setBounds(84, 10, 153, 51);
		paneReport.add(lblNewLabel_3);
		
		JLabel lblIconReport = new JLabel("");
		lblIconReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconReport.setBounds(10, 0, 64, 72);
		lblIconReport.setIcon(new ImageIcon(img_report));
		paneReport.add(lblIconReport);
		
		JPanel paneLogout = new JPanel();
		paneLogout.addMouseListener(new PanelButtonMouseAdapter(paneLogout) {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login frameLogin = new Login();
				frameLogin.setVisible(true);
				dispose();
			}
		});
		paneLogout.setBorder(null);
		paneLogout.setBackground(new Color(211, 211, 211));
		paneLogout.setBounds(0, 571, 247, 72);
		panel.add(paneLogout);
		paneLogout.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Sign out");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_4.setBounds(86, 10, 151, 52);
		paneLogout.add(lblNewLabel_4);
		
		JLabel lblIconOut = new JLabel("");
		lblIconOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconOut.setBounds(10, 0, 66, 72);
		lblIconOut.setIcon(new ImageIcon(img_out));
		paneLogout.add(lblIconOut);
		
		JLabel lblTitle = new JLabel("STUDENT RANKING SYSTEM");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 64));
		lblTitle.setBounds(250, 10, 970, 86);
		contentPane.add(lblTitle);
		
		JLabel lblIconMenu = new JLabel("");
		lblIconMenu.setBounds(250, 98, 970, 572);
		contentPane.add(lblIconMenu);
		lblIconMenu.setIcon(new ImageIcon(img_menu));	
	}
	
	public void menuClicked(JPanel panel) {
	}
		private class PanelButtonMouseAdapter extends MouseAdapter{
			
			JPanel panel;
			public PanelButtonMouseAdapter(JPanel panel) {
				this.panel = panel;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(211, 211, 211));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(new Color(200, 200, 200));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panel.setBackground(new Color(195, 195, 195));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panel.setBackground(new Color(211, 211, 211));
			}
		}
}

