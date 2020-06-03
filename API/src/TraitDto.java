
public class TraitDto {
	private String name;
    private int num_units;
    private int tier_current;
    private int tier_total;

    //»ý¼ºÀÚ
    public TraitDto() {
        super();
    }

    public TraitDto(String name, int num_units, int tier_current, int tier_total) {
        super();
        this.name = name;
        this.num_units = num_units;
        this.tier_current = tier_current;
        this.tier_total = tier_total;
    }

    //getter,setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_units() {
        return num_units;
    }

    public void setNum_units(int num_units) {
        this.num_units = num_units;
    }

    public int getTier_current() {
        return tier_current;
    }

    public void setTier_current(int tier_current) {
        this.tier_current = tier_current;
    }

    public int getTier_total() {
        return tier_total;
    }

    public void setTier_total(int tier_total) {
        this.tier_total = tier_total;
    }

}
