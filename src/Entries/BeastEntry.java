package Entries;

import java.time.LocalDate;

public class BeastEntry {
	public static class ImageEntry {
		private final String altText;
		private final String imageLink;
		private final String title;
		private final int imageWidth;
		private final int imageHeight;
		private final String scrset;
		private final int dataFileWidth;
		private final int dataFileHeight;

		public ImageEntry(String altText, String imageLink, String title, int imageWidth, int imageHeight, String scrset, int dataFileWidth, int dataFileHeight) {
			this.altText = altText;
			this.imageLink = imageLink;
			this.title = title;
			this.imageWidth = imageWidth;
			this.imageHeight = imageHeight;
			this.scrset = scrset;
			this.dataFileWidth = dataFileWidth;
			this.dataFileHeight = dataFileHeight;
		}
		public ImageEntry() {
			this.title = null;
			this.altText = null;
			this.imageLink = null;
			this.imageWidth = -1;
			this.imageHeight = -1;
			this.scrset = null;
			this.dataFileWidth = -1;
			this.dataFileHeight = -1;
		}
		public String getAltText() { return altText; }
		public String getImageLink() { return imageLink; }
		public String getTitle() { return title; }
		public int getImageWidth() { return imageWidth; }
		public int getImageHeight() { return imageHeight; }
		public String getScrset() { return scrset; }
		public int getDataFileWidth() { return dataFileWidth; }
		public int getDataFileHeight() { return dataFileHeight; }
	}
	private final String name;
	private final String altIdentifier;
	private final ImageEntry image;
	private final boolean members;
	private boolean isNew;
	private final LocalDate dateAdded;
	private final int combat;
	private final int health;
	private final int attack;
	private final int defence;
	private final int magic;
	private final int range;
	private final int stabDefence;
	private final int slashDefence;
	private final int crushDefence;
	private final int magicDefence;
	private final int rangeDefence;

	public BeastEntry(String name, ImageEntry image, String altIdentifier, boolean members, boolean isNew, LocalDate dateAdded, int combat, int health, int attack, int defence, int magic, int range, int stabDefence, int slashDefence, int crushDefence, int magicDefence, int rangeDefence) {
		this.name = name;
		this.image = image;
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
		this.stabDefence = stabDefence;
		this.slashDefence = slashDefence;
		this.crushDefence = crushDefence;
		this.magicDefence = magicDefence;
		this.rangeDefence = rangeDefence;
	}

	public BeastEntry(String jsonString) {
		this.name = null;
		this.image = new ImageEntry();
		this.altIdentifier = null;
		this.members = false;
		this.isNew = false;
		this.dateAdded = null;
		this.combat = -1;
		this.health = -1;
		this.attack = -1;
		this.defence = -1;
		this.magic = -1;
		this.range = -1;
		this.stabDefence = -1;
		this.slashDefence = -1;
		this.crushDefence = -1;
		this.magicDefence = -1;
		this.rangeDefence = -1;
	}

	public BeastEntry() {
		this.name = null;
		this.image = new ImageEntry();
		this.altIdentifier = null;
		this.members = false;
		this.isNew = false;
		this.dateAdded = null;
		this.combat = -1;
		this.health = -1;
		this.attack = -1;
		this.defence = -1;
		this.magic = -1;
		this.range = -1;
		this.stabDefence = -1;
		this.slashDefence = -1;
		this.crushDefence = -1;
		this.magicDefence = -1;
		this.rangeDefence = -1;
	}

	public String getName() {
		return name;
	}
	public ImageEntry getImage() { return image; }
	public String getAltIdentifier() { return altIdentifier; }
	public boolean isMembers() {
		return members;
	}
	public boolean isNew() { return isNew; }
	public void setNew(boolean aNew) { isNew = aNew; }
	public LocalDate getDateAdded() { return dateAdded; }
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
	public int getStabDefence() { return stabDefence; }
	public int getSlashDefence() { return slashDefence; }
	public int getCrushDefence() { return crushDefence; }
	public int getMagicDefence() { return magicDefence; }
	public int getRangeDefence() { return rangeDefence; }

	public String toString() {
		return "{"
				+ "\"altText\":\"" + image.altText + "\","
				+ "\"imageLink\":\"" + image.imageLink + "\","
				+ "\"title\":\"" + image.title + "\","
				+ "\"imageWidth\":" + image.imageWidth + ","
				+ "\"imageHeight\":" + image.imageHeight + ","
				+ "\"scrset\":\"" + image.scrset + "\","
				+ "\"dataFileWidth\":" + image.dataFileWidth + ","
				+ "\"dataFileHeight\":" + image.dataFileHeight
				+ "}";
	}
}
