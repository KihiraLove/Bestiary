import Entries.BeastEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {
	private WriteToFile(){}

	public static List<String> SerializeEntriesToJson(List<List<BeastEntry>> entries){
		List<String> jsonStrings = new ArrayList<>();
		for (List<BeastEntry> range : entries){
			jsonStrings.add(SerializeRangeToJson(range));
		}
		return jsonStrings;
	}

	private static String SerializeRangeToJson(List<BeastEntry> range) {
		return "";
	}


	public static void WriteSingleHtmlEntry(String entry){
		try (FileWriter writer = new FileWriter("/data/izzie.html")) {
			writer.write(entry);
			System.out.println("Entry written to izzy.html");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public static void WriteAllHtmlEntries(String[] entries){
		try (FileWriter writer = new FileWriter("/data/jsondata.html")) {
			for (String entry : entries){
				writer.write(entry);
			}
			System.out.println("Entries written to jsondata.html");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
