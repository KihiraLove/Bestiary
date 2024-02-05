import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fetcher {
	int[] ranges = {1, 10, 11, 20, 21, 30, 31, 40, 41, 50, 51, 60, 61, 70, 71, 80, 81, 90, 91, 100, 101, 110, 111, 120, 121, 130, 131, 140, 141, 150, 151, 160, 161, 170, 171, 180, 181, 190, 191, 200, 201, 400, 401, 2000};
	public Fetcher() throws IOException {
		for (int i = 0; i < ranges.length; i+=2) {
			System.out.println("" + ranges[i]+", " + ranges[i+1]);
		}
		List<List<String>> allEntries = new ArrayList<>();
		allEntries.add(Arrays.stream(GetHtmlEntriesInRange(1, 10)).toList());
	}

	private List<BeastEntry> ParseHtmlEntries(String[] htmlEntries){
		List<BeastEntry> entries = new ArrayList<>();
		for (String str : htmlEntries){
			entries.add(ParseHtmlEntry(str));
		}
		return entries;
	}

	private BeastEntry ParseHtmlEntry(String htmlEntry) {

	}

	private String[] GetHtmlEntriesInRange(int lower, int upper) throws IOException{
			HttpURLConnection connection = getHttpURLConnection(lower, upper);
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : "
						+ connection.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String responseLine;
			StringBuilder response = new StringBuilder();

			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine);
			}
			connection.disconnect();

			return SplitToHtmlEntries(CleanUpResponse(response));
	}

	private String[] SplitToHtmlEntries(String response) {
		String[] ret = response.split("</tr>");
		for (int i = 0; i < ret.length; i++) {
			ret[i] = ret[i].replace("<tr>", "");
		}
		return ret;
	}

	private static HttpURLConnection getHttpURLConnection(int lower, int upper)throws IOException {
		String urlFirstPart = "https://oldschool.runescape.wiki/api.php?action=parse&text={{Bestiarytable|mems=All|fromlevel=";
		String urlSecondPart = "|tolevel=";
		String urlThirdPart = "|fromletter=A|toletter=Z|levelselect=Yes|levelsort=Yes|verbose=No|slayeronly=No|onlycat=|attribute=|assignedby=|disco=No|quest=No|dmm=No|showstats=Yes}}&prop=text|limitreportdata&title=Calculator:Bestiary&disablelimitreport=true&contentmodel=wikitext&format=json";

		URL url = new URL(urlFirstPart + lower + urlSecondPart + upper + urlThirdPart);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		return conn;
	}

	private String CleanUpResponse(StringBuilder input){
		return input.toString().split("/></th></tr>")[1].split("</tbody></table></div></div>")[0];
	}
}
