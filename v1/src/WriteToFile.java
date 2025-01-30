import entries.BeastEntry;
import exceptions.FailedDirectoryCreationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {
	private WriteToFile(){}

	public static List<String> serializeEntriesToJson(List<List<BeastEntry>> entries){
		List<String> jsonStrings = new ArrayList<>();
		for (List<BeastEntry> range : entries){
			jsonStrings.add(serializeRangeToJson(range));
		}
		return jsonStrings;
	}

	private static String serializeRangeToJson(List<BeastEntry> range) {
		return "";
	}


	public static void writeSingleHtmlEntry(String entry){
		try (FileWriter writer = new FileWriter("/data/izzie.html")) {
			writer.write(entry);
			System.out.println("Entry written to izzy.html");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public static void logResponse(String entry, String range){
		try (FileWriter writer = new FileWriter(FileLocation.constructPathForLog(range))) {
			writer.write(entry);
			System.out.println("Logged response for range :" + range);
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
        }
	}

	public static void writeAllHtmlEntries(String[] entries){
		try (FileWriter writer = new FileWriter("/data/jsondata.html")) {
			for (String entry : entries){
				writer.write(entry);
			}
			System.out.println("Entries written to jsondata.html");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public static void generateDirectories() throws FailedDirectoryCreationException {
		try {
			Files.createDirectories(FileLocation.getLogsPath());
			System.out.println("Data and Logs directory created");
		} catch (IOException e) {
			throw new FailedDirectoryCreationException("Failed to create directory! " + e.getMessage());
		}
	}
}
