import entries.BeastEntry;
import exceptions.FailedDirectoryCreationException;
import exceptions.HttpResponseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public class Content{
		public String cmcont;
		public String cont;
		public String query;

		Content(String jsonString){

		}

		private static String stripInput(String input){
		}

	}

    public static void main(String[] args) throws IOException, InterruptedException, HttpResponseException, FailedDirectoryCreationException, URISyntaxException {
        HashMap<String, String> parameters = buildParametersMap();

        URL url = new URI("https://oldschool.runescape.wiki/api.php").toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

        DataOutputStream dataOutStream = new DataOutputStream(connection.getOutputStream());
        dataOutStream.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        dataOutStream.flush();
		dataOutStream.close();

		int status = connection.getResponseCode();
		System.out.println(status);
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = inputReader.readLine()) != null) {
			content.append(inputLine);
		}
		inputReader.close();

		connection.disconnect();
		System.out.println(content);

    }

    private static HashMap<String, String> buildParametersMap(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("list", "categorymembers");
        parameters.put("cmtitle", "Category:Monsters");
        parameters.put("action", "query");
        parameters.put("format", "json");
        parameters.put("cmlimit", "500");
        return parameters;
    }
}
