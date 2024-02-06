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
		int imageHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("height=\\\"") + 9, htmlEntry.indexOf("\\\" srcset")));
		String imageSrcset = htmlEntry.substring(htmlEntry.indexOf("srcset=\\\"") + 9, htmlEntry.indexOf("\\\" data-file-width")).replace("/images/", "https://oldschool.runescape.wiki/images/");
		int imageDataFileWidth = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-width=\\\"") + 18, htmlEntry.indexOf("\\\" data-file-height")));
		int imageDataFileHeight = Integer.parseInt(htmlEntry.substring(htmlEntry.indexOf("data-file-height=\\\"") + 19, htmlEntry.indexOf("\\\" /></td><td>")));

		String substringForName = htmlEntry.split("<a")[1];
		String name = substringForName.substring(substringForName.indexOf("title=\\\"") + 8, substringForName.indexOf("</a>")).split(">")[1];
		String altIdentifier = htmlEntry.contains("<i>") ? htmlEntry.substring(htmlEntry.indexOf("<i>") + 3, htmlEntry.indexOf("</i>")) : "NA";
		boolean members = htmlEntry.contains("alt=\\\"Members\\\"");
		boolean aNew = true;
		LocalDate added = LocalDate.now();
		String[] substringsForCombats = htmlEntry.split("/></td><td>")[2].replace("</td>","").split("<td>");

		if(substringsForCombats.length < 6){
			String[] newSubstring = new String[6];
			System.arraycopy(substringsForCombats, 0, newSubstring, 0, substringsForCombats.length);
			for (int i = substringsForCombats.length; i < 6; i++)
			{
				newSubstring[i] = "0";
			}
			substringsForCombats = newSubstring;
		}

		int combat = Integer.parseInt(substringsForCombats[0]);
		int health = Integer.parseInt(substringsForCombats[1]);
		int attack = Integer.parseInt(substringsForCombats[2]);
		int defence = Integer.parseInt(substringsForCombats[3]);
		int magic = Integer.parseInt(substringsForCombats[4]);
		int range = Integer.parseInt(substringsForCombats[5]);

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
