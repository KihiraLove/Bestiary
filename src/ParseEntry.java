import Entries.BeastEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParseEntry {
	private ParseEntry(){}

	public static List<BeastEntry> ParseHtmlToEntries(String[] htmlEntries){
		System.out.println("Parsing entries...");
		List<BeastEntry> entries = new ArrayList<>();
		for (String str : htmlEntries){
			entries.add(ParseHtmlEntry(str));
		}
		return entries;
	}

	private static BeastEntry ParseHtmlEntry(String htmlEntry) {
		String imageAltText = htmlEntry.substring(htmlEntry.indexOf("alt=\\\"") + 6, htmlEntry.indexOf("\\\" src"));
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
			imageTitle = htmlEntry.substring(htmlEntry.indexOf("title=\\\"") + 8, htmlEntry.indexOf("\\\" width"));
			imageWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("width=\\\"") + 8, htmlEntry.indexOf("\\\" height")));
			try {
				imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" srcset")));
			} catch (StringIndexOutOfBoundsException e) {
				imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")));
			}
			imageSrcset = htmlEntry.substring(htmlEntry.indexOf("srcset=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")).replace("/images/", "https://oldschool.runescape.wiki/images/");
			imageDataFileWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-width=\\\"") + 18, htmlEntry.indexOf("\\\" data-file-height")));
			imageDataFileHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-height=\\\"") + 19, htmlEntry.indexOf("\\\" /></td><td>")));
		} else {
			imageAltText = null;
		}
		String substringForName = htmlEntry.split("<a")[1];
		String name = substringForName.substring(substringForName.indexOf("title=\\\"") + 8, substringForName.indexOf("</a>")).split(">")[1];
		String altIdentifier = htmlEntry.contains("<i>") ? htmlEntry.substring(htmlEntry.indexOf("<i>") + 3, htmlEntry.indexOf("</i>")) : "NA";
		boolean members = htmlEntry.contains("alt=\\\"Members\\\"");
		boolean aNew = true;
		LocalDate added = LocalDate.now();
		String[] substringsForCombats;
		try {
			substringsForCombats = htmlEntry.split("/></td><td>")[2].replace("</td>", "").split("<td>");
		} catch(ArrayIndexOutOfBoundsException e){
			substringsForCombats = htmlEntry.split("/></a></div></div></td><td>")[1].replace("</td>", "").split("<td>");
		}

		String[] newSubstring = new String[11];

		/*
		System.out.println(imageAltText);
		System.out.println(imageLink);
		System.out.println(imageTitle);
		System.out.println(imageWidth);
		System.out.println(imageHeight);
		System.out.println(imageSrcset);
		System.out.println(imageDataFileWidth);
		System.out.println(imageDataFileHeight);
		System.out.println(substringForName);
		System.out.println(name);
		System.out.println(altIdentifier);
		System.out.println(members);
		System.out.println(aNew);
		System.out.println(Arrays.toString(substringsForCombats));
		 */
		System.arraycopy(substringsForCombats, 0, newSubstring, 0, substringsForCombats.length);

		for (int i = 0; i < newSubstring.length; i++){
			if (newSubstring[i] == null || newSubstring[i].isEmpty() || newSubstring[i].isBlank()){
				newSubstring[i] = "0";
			}
		}

		/*
		System.out.println(name);
		System.out.println(newSubstring[0]);
		System.out.println(newSubstring[1]);
		System.out.println(newSubstring[2]);
		System.out.println(newSubstring[3]);
		System.out.println(newSubstring[4]);
		System.out.println(newSubstring[5]);
		System.out.println(newSubstring[6]);
		System.out.println(newSubstring[7]);
		System.out.println(newSubstring[8]);
		System.out.println(newSubstring[9]);
		System.out.println(newSubstring[10]);
		System.out.println("------------");
		*/

		int combat = Integer.parseInt(newSubstring[0]);
		int health = Integer.parseInt(newSubstring[1]);
		int attack = Integer.parseInt(newSubstring[2]);
		int defence = Integer.parseInt(newSubstring[3]);
		int magic = Integer.parseInt(newSubstring[4]);
		int range = Integer.parseInt(newSubstring[5]);
		int stabDefence = Integer.parseInt(newSubstring[6]);
		int slashDefence = Integer.parseInt(newSubstring[7]);
		int crushDefence = Integer.parseInt(newSubstring[8]);
		int magicDefence = Integer.parseInt(newSubstring[9]);
		int rangeDefence = Integer.parseInt(newSubstring[10]);

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
