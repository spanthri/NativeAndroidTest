package com.conn;

import com.coke.Utils.Utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by spanthri on 07/03/16.
 */
public class  RestMethodImpl implements RestMethodCalls {

    HttpClient httpClient = null;
    HttpContext localContext = null;

    private static RestMethodImpl restMethodImplInstance = null;
    URL url ;


    public static RestMethodImpl getInstance() {
        if(restMethodImplInstance == null) {
            restMethodImplInstance = new RestMethodImpl();
        }
        return restMethodImplInstance;
    }

    @Override
    public ArrayList requestGetURL(String connUrl) {
         httpClient = new DefaultHttpClient();
         localContext = new BasicHttpContext();
        try {
            url = new URL(connUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(connUrl);

         ArrayList<String> text = new ArrayList();
        try {

            httpGet.setHeader("Content-Type","application/json");
            httpGet.setHeader("DeviceId", "00000000-4a3f-fade-ffff-ffff99d603a9");

            HttpResponse response = httpClient.execute(httpGet, localContext);
            Header[] headers = response.getAllHeaders();

            HttpEntity entity = response.getEntity();

                HashMap<String, String> result = new HashMap<String, String>(headers.length);
                for (Header header : headers) {
                    result.put(header.getName(), header.getValue());
                }

            String userName = result.get("UserName").toString();
            //String authorization = result.get("OAUTH").toString();
            //System.out.println("OAUTH: " + authorization);
            text.add(userName);
            text.add(getResultFromEntity(entity));

        } catch (Exception e) {
            //\return e.getLocalizedMessage();
            System.out.println(e.getLocalizedMessage());

        }

        return text;
    }

    @Override
    public void requestPost(String connUrl) {
        HttpGet httpGet = new HttpGet(connUrl);
        httpClient = new DefaultHttpClient();
        localContext = new BasicHttpContext();
        httpGet.setHeader("Content-Type","application/json");

    }

    @Override
    public void requestDelete() {
        httpClient = new DefaultHttpClient();
        localContext = new BasicHttpContext();
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
