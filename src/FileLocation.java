import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLocation {
    private static final String DATA_DIR = "/data";
    private static final String LOGS_DIR = "/data/logs";

    private FileLocation() {
    }

    public static Path getLogsPath() {
        return Paths.get(new File("").getAbsolutePath().concat(LOGS_DIR));
    }

    public static Path getDataPath() {
        return Paths.get(new File("").getAbsolutePath().concat(DATA_DIR));
    }

    public static String constructPathForLog(String logName) {
        return getLogsPath().toString().concat("/").concat(logName).concat(".txt");
    }

    public static String constructPathFoJsonData(String dataName) {
        return getDataPath().toString().concat("/").concat(dataName).concat(".json");
    }
}