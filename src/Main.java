import java.io.IOException;

public class Main {
    public static void main(String[] args) {
	    try {
		    Fetcher f = new Fetcher();
            String[] entries = f.test();
            WriteToFile.WriteSingleHtmlEntry(entries[0]);
            WriteToFile.WriteAllHtmlEntries(entries);
	    } catch (IOException e) {
		    throw new RuntimeException(e);
	    }
    }
}