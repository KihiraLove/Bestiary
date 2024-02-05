import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	private WriteToFile(){}

	public static void WriteSingleHtmlEntry(String entry){
		try (FileWriter writer = new FileWriter("izzie.json")) {
			writer.write(entry);
			System.out.println("Entry written to izzy.json");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public static void WriteAllHtmlEntries(String[] entries){
		try (FileWriter writer = new FileWriter("jsondata.json")) {
			for (String entry : entries){
				writer.write(entry);
			}
			System.out.println("Entries written to jsondata.json");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
