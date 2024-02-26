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
		String imageLink = htmlEntry.substring(htmlEntry.indexOf("/images/"), htmlEntry.indexOf("\\\" decoding")).replace("/images/", "https://oldschool.runescape.wiki/images/");
		String imageTitle = htmlEntry.substring(htmlEntry.indexOf("title=\\\"") + 8, htmlEntry.indexOf("\\\" width"));
		int imageWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("width=\\\"") + 8, htmlEntry.indexOf("\\\" height")));
		int imageHeight;
		try {
			imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" srcset")));
		} catch(StringIndexOutOfBoundsException e){
			imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")));
		}
		String imageSrcset = htmlEntry.substring(htmlEntry.indexOf("srcset=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")).replace("/images/", "https://oldschool.runescape.wiki/images/");
		int imageDataFileWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-width=\\\"") + 18, htmlEntry.indexOf("\\\" data-file-height")));
		int imageDataFileHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-height=\\\"") + 19, htmlEntry.indexOf("\\\" /></td><td>")));

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
			substringsForCombats = htmlEntry.split("/></td><td>")[1].replace("</td>", "").split("<td>");
		}
		String[] newSubstring = new String[6];
		System.arraycopy(substringsForCombats, 0, newSubstring, 0, substringsForCombats.length);

		for (int i = 0; i < 6; i++){
            System.out.println(newSubstring[i]);
			if (newSubstring[i] == null || newSubstring[i].isEmpty() || newSubstring[i].isBlank()){
				newSubstring[i] = "0";
			}
		}

		System.out.println(name);
		System.out.println(newSubstring[0]);
		System.out.println(newSubstring[1]);
		System.out.println(newSubstring[2]);
		System.out.println(newSubstring[3]);
		System.out.println(newSubstring[4]);
		System.out.println(newSubstring[5]);
		System.out.println("------------");


		int combat = Integer.parseInt(newSubstring[0]);
		int health = Integer.parseInt(newSubstring[1]);
		int attack = Integer.parseInt(newSubstring[2]);
		int defence = Integer.parseInt(newSubstring[3]);
		int magic = Integer.parseInt(newSubstring[4]);
		int range = Integer.parseInt(newSubstring[5]);

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
							range);
	}
}
