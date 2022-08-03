import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.util.Arrays;
import java.util.Vector;
public class Test {

	private static final String Objects = null;
	static Connection con = null;
	static PreparedStatement pst4 = null;
	static PreparedStatement pst5 = null;
	ResultSet rs4 = null;
	ResultSet rs5 = null;

	private static JTable table;

	public static void main(String[] args) throws SQLException {
		String sql4 = "SELECT student.studentID, program.programDesc, student.lastName, student.firstName, student.middleInitial, student.suffix, year.yearDesc, section.sectionDesc, grade.gwa FROM student, program, year, section, grade WHERE student.programID = program.programID AND student.yearID = year.yearID AND student.sectionID = section.sectionID AND student.studentID = grade.studentID;";
		con = DriverManager.getConnection("jdbc:mysql://localhost/studentrank", "root", "");
		pst4 = con.prepareStatement(sql4);

		ResultSet rs4 = pst4.executeQuery();

		String sql5 = "SELECT COUNT(*) AS recordCount FROM grade;";
		pst5 = con.prepareStatement(sql5);
		ResultSet rs5 = pst5.executeQuery();

		ResultSetMetaData stData = (ResultSetMetaData) rs4.getMetaData();

		int q = stData.getColumnCount();

		System.out.println(q);
		rs5.next();
		int count = rs5.getInt("recordCount");
		//rs5.close();	
		System.out.println("MyTable has " + count + " row(s).");

		StudentGrade[] rankings = new StudentGrade[count];

		
		  //DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
		  //RecordTable.setRowCount(0);
		  
		  
		  while (rs4.next()){
			for (int i = 0; i < count; i++){
				rankings[i] = new StudentGrade(rs4.getString(1), rs4.getString(2), rs4.getString(3), rs4.getString(4), rs4.getString(5), rs4.getString(6), rs4.getString(7), rs4.getString(8),rs4.getFloat(9));
				System.out.println((rankings[i].studentID + " " + rankings[i].programDesc + " " + rankings[i].lastName+ " " + rankings[i].firstName+ " " + rankings[i].middleInitial+ " " + rankings[i].suffix+ " " + rankings[i].yearDesc + " " + rankings[i].sectionDesc + " " + rankings[i].gwa));
			}
		  }
		 
		 // for(int i = 0; i < rankings.length; i++){
		//	System.out.println(Arrays.toString(rankings[i]));
		 // 
		 	
	}
}