import java.util.ArrayList;
import java.util.List;

public class UnitDto {
	private ArrayList<Integer> items = new ArrayList<Integer>();
    private String character_id;
    private String name;
    private int rarity;
    private int tier;

    //»ý¼ºÀÚ
    public UnitDto() {
        super();
    }

    public UnitDto(ArrayList<Integer> items, String character_id, String name, int rarity, int tier) {
        super();
        this.items = items;
        this.character_id = character_id;
        this.name = name;
        this.rarity = rarity;
        this.tier = tier;
    }

    //getter,setter
    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public String getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(String character_id) {
        this.character_id = character_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

}
