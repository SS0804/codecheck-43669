package codecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class App {
	public static void main(String[] args) {

		String param = args[0];
		String output = null;

//		for (int i = 0, l = args.length; i < l; i++) {
//
//			if (i == 0) {
//				param = args[i];
//			} else {
//				param = param + args[i];
//			}
//
//		}

		//確認
		System.out.println(param);

		String url = "http://challenge-server.code-check.io/api/hash?q="+param;

		//確認
		System.out.println(url);

		URL connectUrl =null;
		try {

			connectUrl = new URL(url);

			HttpURLConnection connection = null;

			connection = (HttpURLConnection) connectUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			final int status = connection.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(	new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();

				String line;

				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				System.out.println(sb.toString());

				JSONObject jsonObject = new JSONObject(sb.toString());

				System.out.println("q:" + jsonObject.get("q"));
				System.out.println("hash" + jsonObject.get("hash"));

				output = (String) jsonObject.get("hash");

			}

		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//String output = String.format("argv[%s]: %s", i, args[i]);
		System.out.println(output);

	}
}
