import java.time.LocalDate;
import java.util.Date;

public class BeastEntry {
	private final String name;
	private final String imageLink;
	private final String altIdentifier;
	private final boolean members;
	private boolean isNew;
	private final LocalDate dateAdded;
	private final int combat;
	private final int health;
	private final int attack;
	private final int defence;
	private final int magic;
	private final int range;

	public BeastEntry(String name, String imageLink, String altIdentifier, boolean members, boolean isNew, LocalDate dateAdded, int combat, int health, int attack, int defence, int magic, int range) {
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

	public BeastEntry() {
		this.name = "";
		this.imageLink = "";
		this.altIdentifier = "";
		this.members = false;
		this.isNew = false;
		this.dateAdded = LocalDate.now();
		this.combat = 1;
		this.health = 1;
		this.attack = 1;
		this.defence = 1;
		this.magic = 1;
		this.range = 1;
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
	public LocalDate getDateAdded() { return dateAdded; }
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
