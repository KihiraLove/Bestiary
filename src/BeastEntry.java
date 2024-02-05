import java.util.Date;

public class BeastEntry {
	private final String name;
	private final String imageLink;
	private final String altIdentifier;
	private final boolean members;
	private boolean isNew;
	private final Date dateAdded;
	private final int combat;
	private final int health;
	private final int attack;
	private final int defence;
	private final int magic;
	private final int range;

	public BeastEntry(String name, String imageLink, String altIdentifier, boolean members, boolean isNew, Date dateAdded, int combat, int health, int attack, int defence, int magic, int range) {
		this.name = name;
		this.imageLink = imageLink;
		this.altIdentifier = altIdentifier;
		this.members = members;
		this.isNew = isNew;
		this.dateAdded = dateAdded;
		this.combat = combat;
		this.health = health;
		this.attack = attack;
		this.defence = defence;
		this.magic = magic;
		this.range = range;
	}

	public String getName() {
		return name;
	}
	public String getImageLink() { return imageLink; }
	public String getAltIdentifier() { return altIdentifier; }
	public boolean isMembers() {
		return members;
	}
	public boolean isNew() { return isNew; }
	public Date getDateAdded() { return dateAdded; }
	public void setNew(boolean aNew) { isNew = aNew; }
	public int getCombat() {
		return combat;
	}
	public int getHealth() {
		return health;
	}
	public int getAttack() {
		return attack;
	}
	public int getDefence() {
		return defence;
	}
	public int getMagic() {
		return magic;
	}
	public int getRange() {
		return range;
	}
}
