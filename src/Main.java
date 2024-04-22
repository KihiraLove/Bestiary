import entries.BeastEntry;
import exceptions.HttpResponseException;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
	    try {
			Config.getInstance().enableLogging();
		    List<List<BeastEntry>> allEntries = FetchFromApi.fetchAllEntries();
			//WriteToFile.SerializeEntriesToJson(allEntries);

	    } catch (IOException | InterruptedException | HttpResponseException e) {
		    throw new RuntimeException(e);
        }
    }
}