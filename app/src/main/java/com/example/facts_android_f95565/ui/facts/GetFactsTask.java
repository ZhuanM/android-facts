package com.example.facts_android_f95565.ui.facts;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class GetFactsTask extends AsyncTask<Void, Void, String> {

    private static final String FACTS_API_URL = "https://api.api-ninjas.com/v1/facts?limit=20";

    // TODO I changed the API Key for security reasons - go to https://api-ninjas.com/profile to get the new key if needed
    private static final String API_KEY = "cPwnau70jlox/Soi5BRCPg==GvAg949KibL6YJFY";

    private FactsAdapter factsAdapter;

    public GetFactsTask(FactsAdapter factsAdapter) {
        this.factsAdapter = factsAdapter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(FACTS_API_URL);

            // Open a connection to the API
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setRequestProperty("accept", "application/json");
            urlConnection.setRequestProperty("X-Api-Key", API_KEY);

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            String response = convertStreamToString(inputStream);

            urlConnection.disconnect();

            // Return the response
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        // parse the json response
        List<String> facts = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String fact = jsonObject.getString("fact");
                facts.add(fact);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Updates the UI
        factsAdapter.setData(facts);
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}