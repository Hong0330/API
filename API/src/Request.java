
import org.json.simple.*;
import org.json.simple.JSONObject; //JSON��ü�� ����µ� ���
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser; //JSON��ü�� �Ľ��ؿ��µ� ���
import org.json.simple.parser.ParseException; //����ó��

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

public class Request extends Thread{
	
	String API_key = "RGAPI-15a6aa5d-24e6-46b9-9644-c721249c1baa";
    String URL_01 = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/";
    String URL_02 = "https://kr.api.riotgames.com/tft/league/v1/entries/by-summoner/";
    String URL_03 = "https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/";
    String URL_04 = "https://asia.api.riotgames.com/tft/match/v1/matches/";
    String URL_05 = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-puuid/";
    
    SummonerDTO summonerDTO = new SummonerDTO();//��ȯ�� ����
    LeagueEntryDTO leagueEntryDTO = new LeagueEntryDTO();//Ƽ�� ����
    
    ArrayList<MatchDto> matchDto = new ArrayList<MatchDto>();// ��ġ ���� ����
    ArrayList<String> matchList = new ArrayList<String>();	//��ġ ����Ʈ
    
    ArrayList<ArrayList<String>> participantList = new ArrayList<ArrayList<String>>(); //������ ��ȯ�� ����
    ArrayList<String> nameTmp = new  ArrayList<String>();
    int index = 1;
    
   
    public boolean callSummonerDTO(String name) throws ParseException {
    	try {   		
    		name = URLEncoder.encode(name, "UTF-8");  //�ѱ��� ���ڵ��Ͽ� �ν��ϰ� ��
    		
    		URL url = new URL(URL_01 + name + "?api_key=" + API_key);
            System.out.println(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            System.out.println(myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader); //
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                String receiveMsg = buffer.toString();
                
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(receiveMsg);
                setSummonerDTO(jsonObject);
                System.out.println(summonerDTO.getName());
                
                return true;
            }
            else {
                // Error handling code goes here
                System.out.println("�����ڵ� �߻�");
                return false;
            }
    	}
    	catch(IOException e) {
    		System.out.println("�����߻�");
            e.printStackTrace();
            return false;
    	}
    }
    
    public boolean callLeagueEntryDTO(String id) throws ParseException {
    	try {
    		URL url = new URL(URL_02 + id + "?api_key=" + API_key);
            System.out.println(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            System.out.println(myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader); //
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                String receiveMsg = buffer.toString();
                receiveMsg = receiveMsg.replaceAll("[\\[\\]]", ""); //���ȣ ����
                JSONParser jsonParser = new JSONParser();
                
                if("".equals(receiveMsg)) { // TFT�� �ѹ��� �÷��� ���� ���� �� 
                	System.out.println("�÷��� ���� ����");
                	return false;
                }               
                
                JSONObject jsonObject = (JSONObject)jsonParser.parse(receiveMsg);
                
                setLeagueEntryDTO(jsonObject);
                
                System.out.println(leagueEntryDTO.getRank());
                return true;
            }
            else {
                // Error handling code goes here
                System.out.println("�����ڵ� �߻�");
                return false;
            }
    	}
    	catch(IOException e) {
    		System.out.println("�����߻�");
            e.printStackTrace();
            return false;
    	}
    }
    
    public boolean callMatchList(String puuid) throws ParseException {
    	try {
    		URL url = new URL(URL_03 + puuid + "/ids?count=10&api_key=" + API_key);
            System.out.println(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            System.out.println(myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader); //
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                String receiveMsg = buffer.toString();
                
                JSONParser jsonParser = new JSONParser();
                
                JSONArray jsonArray = (JSONArray)jsonParser.parse(receiveMsg);
                
                setMatchList(jsonArray);
                
                System.out.println(matchList.get(0));
                System.out.println(matchList.get(1));
                System.out.println(matchList.get(2));
                return true;
            }
            else {
                // Error handling code goes here
                System.out.println("�����ڵ� �߻�");
                return false;
            }
    	}
    	catch(IOException e) {
    		System.out.println("�����߻�");
            e.printStackTrace();
            return false;
    	}
    }
    
    public boolean callMatch(String matchNumber) throws ParseException {
    	try {
    		URL url = new URL(URL_04 + matchNumber + "?api_key=" + API_key);
            System.out.println(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            System.out.println(myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader); //
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                String receiveMsg = buffer.toString();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(receiveMsg);
                
                setMatch(jsonObject);
                System.out.println("match : " + matchDto.get(matchDto.size()-1).getMetadata().getMatch_id());
                
                //�������� puuid�� �г����� ����
                for(int i = 0 ; i < matchDto.get(matchDto.size()-1).getMetadata().getParticipants().size() ; i++) {
                	if(callParticipantList(matchDto.get(matchDto.size()-1).getMetadata().getParticipants().get(i), matchDto.size()) == false) {
                		System.out.println("�����߻�");
                		return false;
                	}
                }
                return true;
            }
            else {
                // Error handling code goes here
                System.out.println("�����ڵ� �߻�");
                return false;
            }
    	}
    	catch(IOException e) {
    		System.out.println("�����߻�");
            e.printStackTrace();
            return false;
    	}
    }
    
    public boolean callParticipantList(String puuid,int number) throws ParseException {
    	try {   		   		
    		URL url = new URL(URL_05 + puuid + "?api_key=" + API_key);
            System.out.println(url);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            System.out.println(myConnection.getResponseCode());
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader); //
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                String receiveMsg = buffer.toString();
                
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(receiveMsg);
                
                setParticipantList(jsonObject, number);
                
                return true;
            }
            else {
                // Error handling code goes here
                System.out.println("�����ڵ� �߻�");
                return false;
            }
    	}
    	catch(IOException e) {
    		System.out.println("�����߻�");
            e.printStackTrace();
            
            return false;
    	}
    }
    
    
    public void setSummonerDTO(JSONObject jsonObject) {
        summonerDTO.setAccountId((String)jsonObject.get("accountId"));
        summonerDTO.setProfileIconId(((Long)jsonObject.get("profileIconId")).intValue());
        summonerDTO.setRevisionDate((Long)jsonObject.get("revisionDate"));
        summonerDTO.setName((String)jsonObject.get("name"));
        summonerDTO.setId((String)jsonObject.get("id"));
        summonerDTO.setPuuid((String)jsonObject.get("puuid"));
        summonerDTO.setSummonerLevel(((Long)jsonObject.get("summonerLevel")).intValue());
    }
    
    public void setLeagueEntryDTO(JSONObject jsonObject) {
        leagueEntryDTO.setLeagueId((String)jsonObject.get("leagueId"));
        leagueEntryDTO.setQueueType((String)jsonObject.get("queueType"));
        leagueEntryDTO.setTier((String)jsonObject.get("tier"));
        leagueEntryDTO.setRank((String)jsonObject.get("rank"));
        leagueEntryDTO.setSummonerId((String)jsonObject.get("summonerId"));
        leagueEntryDTO.setSummonerName((String)jsonObject.get("summonerName"));
        leagueEntryDTO.setLeaguePoints(((Long)jsonObject.get("leaguePoints")).intValue());
        leagueEntryDTO.setWins(((Long)jsonObject.get("wins")).intValue());
        leagueEntryDTO.setLosses(((Long)jsonObject.get("losses")).intValue());
        leagueEntryDTO.setVeteran((Boolean)jsonObject.get("veteran"));
        leagueEntryDTO.setInactive((Boolean)jsonObject.get("inactive"));
        leagueEntryDTO.setFreshBlood((Boolean)jsonObject.get("freshBlood"));
        leagueEntryDTO.setHotStreak((Boolean)jsonObject.get("hotStreak"));
    }
    
    public void setMatchList(JSONArray jsonArray) throws ParseException {
    	for(int i = 0; i < jsonArray.size(); i++) {
            matchList.add((String) jsonArray.get(i));
            callMatch(matchList.get(i));
        }
    }

    public void setMatch(JSONObject jsonObject) throws ParseException {
    	MatchDto tmp = new MatchDto();
    	
    	//metadata �Ľ�
    	MetadataDto metadataTmp = new MetadataDto();
    	JSONObject metadataObject = (JSONObject) jsonObject.get("metadata");
    	String data_version = (String) metadataObject.get("data_version");
    	String match_id = (String) metadataObject.get("match_id");  	
    	ArrayList<String> participant = (ArrayList<String>) metadataObject.get("participants");
    	metadataTmp.setData_version(data_version);
    	metadataTmp.setMatch_id(match_id);
    	metadataTmp.setParticipants(participant);
    	tmp.setMetadata(metadataTmp);
    	//metadata �Ľ� �Ϸ�
    	
    	//info �Ľ�
    	InfoDto infoTmp = new InfoDto();
    	JSONObject infoObject = (JSONObject) jsonObject.get("info");
    	long game_datetime = (long) infoObject.get("game_datetime");
    	double game_length = (double) infoObject.get("game_length");
    	String game_variation = (String)infoObject.get("game_variation"); // ���ϰ� ���� 
    	String game_version = (String) infoObject.get("game_version");   
    	int queue_id = ((Long)infoObject.get("queue_id")).intValue();
    	int tft_set_number = ((Long)infoObject.get("tft_set_number")).intValue();
    	
    	infoTmp.setGame_datetime(game_datetime);
    	infoTmp.setGame_length((float)game_length);
    	infoTmp.setGame_variation(game_variation);
    	infoTmp.setGame_version(game_version);
    	infoTmp.setQueue_id(queue_id);
    	infoTmp.setTft_set_number(tft_set_number);
    	
    	//participants�Ľ�	
    	ArrayList<ParticipantDto> participants = new ArrayList<ParticipantDto>(); //���������� ������ ����Ʈ
    	
    	ArrayList<JSONObject> participantsTmp = (ArrayList<JSONObject>) infoObject.get("participants");
    	for(int i = 0 ; i < participantsTmp.size() ; i++) {		
    		ParticipantDto p = new ParticipantDto();
    		
    		JSONObject jsonTmp = participantsTmp.get(i);
    		
    		
    		// companion�Ľ�
    		CompanionDto companion = new CompanionDto(); //���� ���� 
    		
    		JSONObject companionTmp = (JSONObject) jsonTmp.get("companion");

    		String content_ID = (String) companionTmp.get("content_ID");
    		int skin_ID = ((Long)companionTmp.get("skin_ID")).intValue();
    		String species = (String) companionTmp.get("species");
    		
    		companion.setContent_ID(content_ID);
    		companion.setSkin_ID(skin_ID);
    		companion.setSpecies(species);
    		
    		int gold_left = ((Long)jsonTmp.get("gold_left")).intValue();
    		int last_round = ((Long)jsonTmp.get("last_round")).intValue();
    		int level = ((Long)jsonTmp.get("level")).intValue();
    		int placement = ((Long)jsonTmp.get("placement")).intValue();
    		int players_eliminated = ((Long)jsonTmp.get("players_eliminated")).intValue();
    		String puuid = (String) jsonTmp.get("puuid");
    		double time_eliminated = (double) jsonTmp.get("time_eliminated");
    		int total_damage_to_players = ((Long)jsonTmp.get("total_damage_to_players")).intValue();
    		
    		p.setCompanion(companion);
    		p.setGold_left(gold_left);
    		p.setLast_round(last_round);
    		p.setLevel(level);
    		p.setPlacement(placement);
    		p.setPlayers_eliminated(players_eliminated);
    		p.setPuuid(puuid);
    		p.setTime_eliminated((float)time_eliminated);
    		p.setTotal_damage_to_players(total_damage_to_players);
    		
    		// �ó��� �Ľ�
    		ArrayList<TraitDto> traits = new ArrayList<TraitDto>(); //���� ����
    		
    		ArrayList<JSONObject> traitsTmp = (ArrayList<JSONObject>) jsonTmp.get("traits");
    		for(int j = 0 ; j < traitsTmp.size() ; j++) {
    			TraitDto t = new TraitDto();
    			
    			JSONObject objectTmp = traitsTmp.get(j);
    			
    			String nameString = (String) objectTmp.get("name");
    			int num_units = ((Long)objectTmp.get("num_units")).intValue();
    			int tier_current = ((Long)objectTmp.get("tier_current")).intValue();
    			if(objectTmp.get("tier_total") != null) {
    				int tier_total = ((Long)objectTmp.get("tier_total")).intValue();
    				t.setTier_total(tier_total);
    			}
    			
    			t.setName(nameString);
    			t.setNum_units(num_units);
    			t.setTier_current(tier_current);
    			
    			traits.add(t);
    		}
    		//���� �Ľ�
    		ArrayList<UnitDto> units = new ArrayList<UnitDto>(); //���� ����
    		
    		ArrayList<JSONObject> unitsTmp = (ArrayList<JSONObject>) jsonTmp.get("units");
    		
    		for(int k = 0 ; k < unitsTmp.size() ; k++) {
    			UnitDto u = new UnitDto();
    			
    			JSONObject objectTmp = unitsTmp.get(k);
    			
    			ArrayList<Integer> items = (ArrayList<Integer>) objectTmp.get("items");
    			String character_id = (String) objectTmp.get("character_id");
    			String name = (String) objectTmp.get("name");
    			int rarity = ((Long)objectTmp.get("rarity")).intValue();
    			int tier = ((Long)objectTmp.get("tier")).intValue();
    			
    			u.setCharacter_id(character_id);
    			u.setItems(items);
    			u.setName(name);
    			u.setRarity(rarity);
    			u.setTier(tier);
    			
    			units.add(u);
    		}
    		p.setTraits(traits);
    		p.setUnits(units);
    		participants.add(p);
    	}
    	
    	infoTmp.setParticipants(participants);
    	tmp.setInfo(infoTmp);
    	matchDto.add(tmp);
    	infoTmp = new InfoDto();
    }
    
    public void setParticipantList(JSONObject jsonObject,int number) {
    	//test.get(number-1).add((String) jsonObject.get("name"));
    	if(changeNum(number)) {
    		nameTmp.add((String) jsonObject.get("name"));
    	}
    	else {
    		participantList.add(nameTmp);
    		nameTmp = new ArrayList<String>();
    		nameTmp.add((String) jsonObject.get("name"));
    	}
    	System.out.println("number : " + number + " name : " +(String) jsonObject.get("name"));
    }
    
    public boolean changeNum(int number) {
    	if(index == number) {
    		return true;
    	}
    	else {
    		index = number;
    		return false;
    	}
    }
    
    public void reset() {
    	summonerDTO = new SummonerDTO();//��ȯ�� ����
        leagueEntryDTO = new LeagueEntryDTO();//Ƽ�� ����
        
        matchDto = new ArrayList<MatchDto>();// ��ġ ���� ����
        matchList = new ArrayList<String>();	//��ġ ����Ʈ
        
        participantList = new ArrayList<ArrayList<String>>();
        nameTmp = new  ArrayList<String>();
        index = 1;
    }
}

