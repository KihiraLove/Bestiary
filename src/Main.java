import entries.BeastEntry;
import exceptions.FailedDirectoryCreationException;
import exceptions.HttpResponseException;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, HttpResponseException, FailedDirectoryCreationException {
        WriteToFile.generateDirectories();
        Config.getInstance().enableLogging();
        List<List<BeastEntry>> allEntries = FetchFromApi.fetchAllEntries();
        //WriteToFile.SerializeEntriesToJson(allEntries);

    }
}