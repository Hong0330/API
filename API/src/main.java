import org.json.simple.parser.ParseException;

import java.sql.*;

public class main {
	public static void main(String[] args) throws InterruptedException {
		Request r = new Request();
		sql sql = new sql();
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			sql.connect();
			
			
			
			if(r.callSummonerDTO("hide on bush")) {
				if(r.callLeagueEntryDTO(r.summonerDTO.getId())) {
					if(r.callMatchList(r.summonerDTO.getPuuid())) {
						for(int i = 0 ; i < r.matchDto.size(); i++) {
							System.out.println("i : " + i + " Set Number : "+ r.matchDto.get(i).getInfo().getGame_variation());
						}
					}
				}
			}
			else { //문제 발생
				//문제 처리
				
			}
			
			sql.insert();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
