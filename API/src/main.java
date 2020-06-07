import org.json.simple.parser.ParseException;

import java.sql.*;
import java.util.ArrayList;

public class main {
	public static void main(String[] args) throws InterruptedException {
		Request r = new Request();
		sql sql = new sql();
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			sql.connect();
			
			
			
			if(r.callSummonerDTO("롤처음하는휴먼")) {
				if(r.callLeagueEntryDTO(r.summonerDTO.getId())) {
					if(r.callMatchList(r.summonerDTO.getPuuid())) {
						for(int i = 0 ; i < r.matchDto.size(); i++) {
							System.out.println("i : " + i + " Set Number : "+ r.matchDto.get(i).getInfo().getGame_variation());
						}
						
						
						sql.insertSummoner_info("test", "test", r.summonerDTO.getName());
						
						//i = 매치번호인덱스, j = 유저인덱스
						for(int i = 0; i < r.matchList.size() ; i++) {
							sql.insertMatch_info(r.summonerDTO.getName(), r.matchList.get(i), r.matchDto.get(i).getInfo().getGame_length(), r.matchDto.get(i).getInfo().getGame_variation());
							for(int j = 0 ;j < r.matchDto.get(i).getInfo().getParticipants().size(); j++) {
								sql.insertUser_info(r.matchList.get(i), r.participantList.get(i).get(j), r.matchDto.get(i).getInfo().getParticipants().get(j).getGold_left(), r.matchDto.get(i).getInfo().getParticipants().get(j).getLast_round(), r.matchDto.get(i).getInfo().getParticipants().get(j).getLevel(), r.matchDto.get(i).getInfo().getParticipants().get(j).getPlayers_eliminated());
								for(int k =0 ; k < r.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().size(); k++) {
									sql.insertTrait_info(r.matchList.get(i), r.participantList.get(i).get(j), k, r.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getName(), r.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getNum_units(), r.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getTier_current());
								}
								for(int l = 0 ; l < r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().size(); l++) {
									int[] tmp = new int[3];
									tmp = r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems();
									int item_1 = 0;
									int item_2 = 0;
									int item_3 = 0;
									switch(tmp.length) {
									case 0:
										break;
									case 1:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										item_1 = tmp[0];
										break;
									case 2:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										//item_2 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(1)).intValue();
										item_1 = tmp[0];
										item_2 = tmp[1];
										break;
									case 3:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										//item_2 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(1)).intValue();
										//item_3 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(2)).intValue();
										item_1 = tmp[0];
										item_2 = tmp[1];
										item_3 = tmp[2];
										break;		
									}
									sql.insertUnit_info(r.matchList.get(i), r.participantList.get(i).get(j), l, r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getCharacter_id(), r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getTier(), item_1, item_2, item_3);
									//r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l)
								}
							}
							/*
							sql.insert_matchlist(r.summonerDTO.getName(), r.matchList.get(i));
							sql.insert_match(r.summonerDTO.getName(),r.matchList.get(i), r.matchObject.get(i));
							for(int j = 0 ;j < r.participantList.get(i).size() + 1 ; j++) {
								sql.insert_userlist(r.matchList.get(i), r.participantList.get(i).get(j));
							}
							*/
						}
						
						
					}
				}
			}
			else { //문제 발생
				//문제 처리
				
			}
			
			System.out.println("DB업데이트 성공");
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
