import Entries.BeastEntry;
import Entries.Ranges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchFromApi {
	private FetchFromApi() {}

	public static String[] test() throws IOException {
		return GetHtmlEntriesInRange(1, 10);
	}

	public static List<List<BeastEntry>> FetchAllEntries() throws IOException, InterruptedException {
		Ranges ranges = Ranges.getInstance();
		List<List<BeastEntry>> allEntries = new ArrayList<>();

		for (int i = 0; i < ranges.GetLengthOfRanges(); i++) {
			allEntries.add(ParseEntry.ParseHtmlToEntries(GetHtmlEntriesInRange(ranges.GetLower(i), ranges.GetUpper(i))));
			System.out.println("Waiting 1 second to avoid DDOSing the OSRS WIKI");
			Thread.sleep(1000);
		}

		return allEntries;
	}

	private static String[] GetHtmlEntriesInRange(int lower, int upper) throws IOException{
		System.out.println("Fetching from API in range: " + lower + " - " + upper);
		HttpURLConnection connection = getHttpURLConnection(lower, upper);
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
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

	private static String[] SplitToHtmlEntries(String response) {
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

	private static String CleanUpResponse(StringBuilder input){
		return input.toString().split("/></th></tr>")[1].split("</tbody></table></div></div>")[0];
	}
}
