public class BeastEntry {
	private final String name;
	private final String imageLink;
	private final boolean members;
	private final int combat;
	private final int health;
	private final int attack;
	private final int defence;
	private final int magic;
	private final int range;

	public BeastEntry(String name, String imageLink, boolean members, int combat, int health, int attack, int defence, int magic, int range) {
		this.name = name;
		this.imageLink = imageLink;
		this.members = members;
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

	public String getImageLink() {
		return imageLink;
	}

	public boolean isMembers() {
		return members;
	}

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
