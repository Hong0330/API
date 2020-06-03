import org.json.simple.parser.ParseException;

public class main {
	public static void main(String[] args) throws InterruptedException {
		Request r = new Request();
		try {
			r.callSummonerDTO("hide on bush");
			r.callLeagueEntryDTO(r.summonerDTO.getId());
			r.callMatchList(r.summonerDTO.getPuuid());
			for(int i = 0 ; i < r.matchDto.size(); i++) {
				System.out.println("i : " + i + " Set Number : "+ r.matchDto.get(i).getInfo().getGame_variation());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
