package social.twittersample;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tarun Varshney on 10/14/2015.
 */
public class NetworkRequestTask extends AsyncTask<String, Void, String> {

    private CallbackWithObject mCallback;

    public NetworkRequestTask(CallbackWithObject callbackWithObject) {
        this.mCallback = callbackWithObject;
    }

    @Override
    protected String doInBackground(String... params) {

        String response = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(urlConnection.getInputStream());
                Log.v("Response string", response);

            } else {
                Log.v("TAG", "Response code:" + responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return response;
    }


    @Override
    protected void onPreExecute() {

    }

    protected void onPostExecute(String result) {
        mCallback.onResult(result);
        Log.v("TAG", "Result :" + result);
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
