import Entries.BeastEntry;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
	    try {
		    List<List<BeastEntry>> allEntries = FetchFromApi.FetchAllEntries();
			WriteToFile.SerializeEntriesToJson(allEntries);

	    } catch (IOException | InterruptedException e) {
		    throw new RuntimeException(e);
	    }
    }
}