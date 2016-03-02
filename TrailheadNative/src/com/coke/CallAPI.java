package com.coke;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;

import com.coke.restobjects.Accounts;
import com.conn.ResultInterface;
import com.salesforce.androidsdk.rest.RestRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spanthri on 01/03/16.
 */
class CallApi extends AsyncTask<String, Void, String> {
    String data ="";
    String content ="";
    URL url ;
    ArrayList listData = new ArrayList();
    public ResultInterface delegate = null;

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        Widget result = new Widget();
        String resultToDisplay = "";

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(url.toString());
        String text = null;
        try {
            //RestRequest restReq = new RestRequest();

            httpGet.setHeader("Content-Type","application/json");
            HttpResponse response = httpClient.execute(httpGet, localContext);

            HttpEntity entity = response.getEntity();

            text = getResultFromEntity(entity);

        } catch (Exception e) {
            return e.getLocalizedMessage();

        }

        return text;


    }


    protected void onPostExecute(String results) {
        if (results!=null) {
            Accounts[] accData = null;
            //JSONObject jsonResponse;
            try {
                System.out.println(" results " +results);
                JSONArray jsonArry = new JSONArray(results);
                int length = jsonArry.length();
                 accData = new Accounts[length];
                for (int i=0; i< length ; i++) {
                    JSONObject child  = jsonArry.getJSONObject(i);
                    String name = child.getString("Name");
                    String id = child.getString("Id");
                    System.out.println(name +  " Id " + id);
                    //listData.add(name);
                    accData[i] = new Accounts(name,id);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //delegate.processFinish(listData);
            delegate.processFinish(accData);
           // ListView et = (ListView)findViewById(R.id.AccountListView);

        }

    }



    protected String getResultFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

        InputStream in = entity.getContent();

        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];

            n =  in.read(b);

            if (n>0) out.append(new String(b, 0, n));

        }

        return out.toString();

    }
}


/*BufferedReader br = null;
        URL url;
        try {
             url = new URL(params[0]);

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWr = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWr.write(data);
            outputStreamWr.flush();


            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sbf  = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(System.getProperty("line.separator"));
            }

            String content = sbf.toString();


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null; */