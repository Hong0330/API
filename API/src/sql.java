import java.sql.*;

public class sql {
	String url = "jdbc:mysql://localhost:3306/tft_data?serverTimezone=Asia/Seoul";
	Connection con = null;
	PreparedStatement pstatement = null;
	
	String user = "root";
	String password = "1234";
	
	String query = "insert into summoner_info(ID, password, TFT_name) values(?, ?, ?)";
	
	public void connect() {
		//目池记 积己
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("目池记 己傍");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		try {
			pstatement = con.prepareStatement(query);
			pstatement.setString(1, "testid");
			pstatement.setString(2, "testpw");
			pstatement.setString(3, "testname");
			
			pstatement.execute();
			
			pstatement.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

