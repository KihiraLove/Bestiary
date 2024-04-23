import entries.BeastEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseEntry {
	private ParseEntry(){}

	public static List<BeastEntry> parseHtmlToEntries(String[] htmlEntries){
		System.out.println("Parsing entries...");
		List<BeastEntry> entries = new ArrayList<>();
		for (String str : htmlEntries){
			entries.add(parseHtmlEntry(str));
		}
		return entries;
	}

	private static BeastEntry parseHtmlEntry(String htmlEntry) {
		boolean isLogging = Config.getInstance().isLoggingEnabled();
		String imageAltText = htmlEntry.substring(htmlEntry.indexOf("alt=\\\"") + 6, htmlEntry.indexOf("\\\" src"));
		if (isLogging){
			System.out.println("Parsed imageAltText: " + imageAltText);
		}
		String imageLink = null;
		String imageTitle = null;
		int imageWidth = 0;
		int imageHeight = 0;
		String imageSrcset = null;
		int imageDataFileWidth = 0;
		int imageDataFileHeight = 0;
		// In case of the entry not containing image data, the parser catches the icon data, skip filling image fields if there is no data
		if (!imageAltText.contains("Member icon.png")) {
			imageLink = htmlEntry.substring(htmlEntry.indexOf("/images/"), htmlEntry.indexOf("\\\" decoding")).replace("/images/", "https://oldschool.runescape.wiki/images/");
			if (isLogging){
				System.out.println("Parsed imageLink: " + imageLink);
			}
			imageTitle = htmlEntry.substring(htmlEntry.indexOf("title=\\\"") + 8, htmlEntry.indexOf("\\\" width"));
			if (isLogging){
				System.out.println("Parsed imageTitle: " + imageTitle);
			}
			imageWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("width=\\\"") + 8, htmlEntry.indexOf("\\\" height")));
			if (isLogging){
				System.out.println("Parsed imageWidth: " + imageWidth);
			}
			try {
				imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" srcset")));
				if (isLogging){
					System.out.println("Parsed imageHeight: " + imageHeight);
				}
			} catch (StringIndexOutOfBoundsException e) {
				imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")));
				if (isLogging){
					System.out.println("Parsed imageHeight, but caught StringIndexOutOfBoundsException: " + imageHeight);
				}
			}
			imageSrcset = htmlEntry.substring(htmlEntry.indexOf("srcset=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")).replace("/images/", "https://oldschool.runescape.wiki/images/");
			if (isLogging){
				System.out.println("Parsed imageSrcset: " + imageSrcset);
			}
			imageDataFileWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-width=\\\"") + 18, htmlEntry.indexOf("\\\" data-file-height")));
			if (isLogging){
				System.out.println("Parsed imageDataFileWidth: " + imageDataFileWidth);
			}
			imageDataFileHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-height=\\\"") + 19, htmlEntry.indexOf("\\\" /></td><td>")));
			if (isLogging){
				System.out.println("Parsed imageDataFileHeight: " + imageDataFileHeight);
			}
		} else {
			imageAltText = null;
		}
		String substringForName = htmlEntry.split("<a")[1];
		if (isLogging){
			System.out.println("substringForName: " + substringForName);
		}
		String name = substringForName.substring(substringForName.indexOf("title=\\\"") + 8, substringForName.indexOf("</a>")).split(">")[1];
		if (isLogging){
			System.out.println("Parsed name: " + name);
		}
		String altIdentifier = htmlEntry.contains("<i>") ? htmlEntry.substring(htmlEntry.indexOf("<i>") + 3, htmlEntry.indexOf("</i>")) : "NA";
		if (isLogging){
			System.out.println("Parsed altIdentifier: " + altIdentifier);
		}
		boolean members = htmlEntry.contains("alt=\\\"Members\\\"");
		if (isLogging){
			System.out.println("Parsed members: " + members);
		}
		boolean aNew = true;
		LocalDate added = LocalDate.now();
		String[] substringsForCombats;
		try {
			substringsForCombats = htmlEntry.split("/></td><td>")[2].replace("</td>", "").split("<td>");
			if (isLogging){
				System.out.println("substringsForCombats: " + Arrays.stream(substringsForCombats));
			}
		} catch(ArrayIndexOutOfBoundsException e){
			substringsForCombats = htmlEntry.split("/></a></div></div></td><td>")[1].replace("</td>", "").split("<td>");
			if (isLogging){
				System.out.println("substringsForCombats but caught ArrayIndexOutOfBoundsException: " + Arrays.stream(substringsForCombats));
			}
		}

		String[] newSubstring = new String[11];
		System.arraycopy(substringsForCombats, 0, newSubstring, 0, substringsForCombats.length);

		if (isLogging){
			System.out.println("newSubstring: " + Arrays.stream(newSubstring));
		}

		for (int i = 0; i < newSubstring.length; i++){
			if (newSubstring[i] == null || newSubstring[i].isEmpty() || newSubstring[i].isBlank()){
				newSubstring[i] = "0";
			}
		}

		if (isLogging){
			System.out.println("newSubstring: " + Arrays.stream(newSubstring));
		}

		int combat = Integer.parseInt(newSubstring[0]);
		if (isLogging){
			System.out.println("combat: " + combat);
		}
		int health = Integer.parseInt(newSubstring[1]);
		if (isLogging){
			System.out.println("health: " + health);
		}
		int attack = Integer.parseInt(newSubstring[2]);
		if (isLogging){
			System.out.println("attack: " + attack);
		}
		int defence = Integer.parseInt(newSubstring[3]);
		if (isLogging){
			System.out.println("defence: " + defence);
		}
		int magic = Integer.parseInt(newSubstring[4]);
		if (isLogging){
			System.out.println("magic: " + magic);
		}
		int range = Integer.parseInt(newSubstring[5]);
		if (isLogging){
			System.out.println("range: " + range);
		}
		int stabDefence = Integer.parseInt(newSubstring[6]);
		if (isLogging){
			System.out.println("stabDefence: " + stabDefence);
		}
		int slashDefence = Integer.parseInt(newSubstring[7]);
		if (isLogging){
			System.out.println("slashDefence: " + slashDefence);
		}
		int crushDefence = Integer.parseInt(newSubstring[8]);
		if (isLogging){
			System.out.println("crushDefence: " + crushDefence);
		}
		int magicDefence = Integer.parseInt(newSubstring[9]);
		if (isLogging){
			System.out.println("magicDefence: " + magicDefence);
		}
		int rangeDefence = Integer.parseInt(newSubstring[10]);
		if (isLogging){
			System.out.println("rangeDefence: " + rangeDefence);
		}

		return new BeastEntry(name,
							new BeastEntry.ImageEntry(imageAltText,
														imageLink,
														imageTitle,
														imageWidth,
														imageHeight,
														imageSrcset,
														imageDataFileWidth,
														imageDataFileHeight),
							altIdentifier,
							members,
							aNew,
							added,
							combat,
							health,
							attack,
							defence,
							magic,
							range,
							stabDefence,
							slashDefence,
							crushDefence,
							magicDefence,
							rangeDefence);
	}
}
