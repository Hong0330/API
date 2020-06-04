import java.util.ArrayList;

public class MetadataDto {
	private String data_version;
    private String match_id;
    private ArrayList<String> participants = new ArrayList<String>();	//�����ڵ��� puuid, �� ����Ʈ�� Ȱ���Ͽ� API_05�� ȣ���Ͽ� �г����� ã��

    //������
    public MetadataDto() {
        super();
    }

    public MetadataDto(String data_version, String match_id, ArrayList<String> participants) {
        super();
        this.data_version = data_version;
        this.match_id = match_id;
        this.participants = participants;
    }
    //getter,setter

    public String getData_version() {
        return data_version;
    }

    public void setData_version(String data_version) {
        this.data_version = data_version;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}
