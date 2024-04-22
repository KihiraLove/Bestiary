import entries.BeastEntry;
import entries.Ranges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchFromApi {
	private FetchFromApi() {}

	public static String[] test() throws IOException, HttpResponseException {
		return getHtmlEntriesInRange(1, 10);
	}

	public static List<List<BeastEntry>> fetchAllEntries() throws IOException, InterruptedException, HttpResponseException {
		Ranges ranges = Ranges.getInstance();
		List<List<BeastEntry>> allEntries = new ArrayList<>();

		for (int i = 0; i < ranges.getLengthOfRanges(); i++) {
			allEntries.add(ParseEntry.ParseHtmlToEntries(getHtmlEntriesInRange(ranges.getLower(i), ranges.getUpper(i))));
			System.out.println("Waiting 0.2 second to avoid DDOSing the OSRS WIKI");
			Thread.sleep(200);
		}

		return allEntries;
	}

	private static String[] getHtmlEntriesInRange(int lower, int upper) throws IOException, HttpResponseException {
		System.out.println("Fetching from API in range: " + lower + " - " + upper);
		HttpURLConnection connection = getHttpURLConnection(lower, upper);
		if (connection.getResponseCode() != 200) {
			throw new HttpResponseException("Failed : HTTP Error code : " + connection.getResponseCode());
		}

		InputStreamReader in = new InputStreamReader(connection.getInputStream());
		BufferedReader br = new BufferedReader(in);
		String responseLine;
		StringBuilder response = new StringBuilder();

		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine);
		}
		connection.disconnect();

		return splitToHtmlEntries(cleanUpResponse(response));
	}

	private static String[] splitToHtmlEntries(String response) {
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

	private static String cleanUpResponse(StringBuilder input){
		return input.toString().split("/></a></th></tr><tr>")[1].split("</tbody></table></div></div>")[0];
	}
}
