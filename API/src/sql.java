import java.sql.*;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class sql {
	String url = "jdbc:mysql://localhost:3306/tft_data?serverTimezone=Asia/Seoul";
	Connection con = null;
	PreparedStatement pstatement = null;
	
	String user = "root";
	String password = "1234";
	
	String summoner_info = "insert into summoner_info(ID, password, TFT_name) values(?, ?, ?)";
	String match_info = "insert into match_info(TFT_name, match_id, game_length, game_variation) values(?, ?, ?, ?)";
	String user_info = "insert into user_info(match_id, user_name, gold_left, last_round, level, player_eliminated) values(?, ?, ?, ?, ?, ?)";
	String trait_info = "insert into trait_info(match_id, user_name, trait_no, trait_name, num_units, tier_current) values(?, ?, ?, ?, ?, ?)";
	String unit_info = "insert into unit_info(match_id, user_name, unit_no, character_id, tier, item_1, item_2, item_3) values(?, ?, ?, ?, ?, ?, ?, ?)";
	
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
	
	public void insertSummoner_info(String id,String pw,String name) {
		try {
			pstatement = con.prepareStatement(summoner_info);
			pstatement.setString(1, id);
			pstatement.setString(2, pw);
			pstatement.setString(3, name);
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertMatch_info(String name, String match_id, float game_length, String game_variation) {
		try {
			pstatement = con.prepareStatement(match_info);
			pstatement.setString(1, name);
			pstatement.setString(2, match_id);
			pstatement.setFloat(3, game_length);
			pstatement.setString(4, game_variation);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertUser_info(String match_id, String user_name, int gold_left, int last_round, int level, int player_eliminated) {
		try {
			
			pstatement = con.prepareStatement(user_info);
			pstatement.setString(1, match_id);
			pstatement.setString(2, user_name);
			pstatement.setInt(3, gold_left);
			pstatement.setInt(4, last_round);
			pstatement.setInt(5, level);
			pstatement.setInt(6, player_eliminated);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertTrait_info(String match_id, String user_name, int trait_no, String trait_name, int num_units, int tier_current) {
		try {
			
			pstatement = con.prepareStatement(trait_info);
			pstatement.setString(1, match_id);
			pstatement.setObject(2, user_name);
			pstatement.setInt(3, trait_no);
			pstatement.setString(4, trait_name);
			pstatement.setInt(5, num_units);
			pstatement.setInt(6, tier_current);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertUnit_info(String match_id, String user_name, int unit_no, String character_id, int tier, int item_1, int item_2, int item_3) {
		try {
			
			pstatement = con.prepareStatement(unit_info);
			pstatement.setString(1, match_id);
			pstatement.setObject(2, user_name);
			pstatement.setInt(3, unit_no);
			pstatement.setString(4, character_id);
			pstatement.setInt(5, tier);
			pstatement.setInt(6, item_1);
			pstatement.setInt(7, item_2);
			pstatement.setInt(8, item_3);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnect() {
		try {
			pstatement.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

