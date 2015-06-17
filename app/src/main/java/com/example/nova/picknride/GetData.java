package com.example.nova.picknride;
import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

class GetData extends AsyncTask<String, Integer, Long> {
    protected Long doInBackground(String... data) {
    	
    	long a=0;
    	HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost("http://192.168.43.216:8080/PicknRide/rest/login/register/");

        Log.d("httpPost", "HttpPost ok");
        // Building post parameters
        // key and value pair

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("name", data[0]));
        nameValuePair.add(new BasicNameValuePair("pwd", data[1]));


        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            Log.d("Entity", "SET");

        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            response.getEntity().writeTo(baos);
            Log.d("Http:", baos.toString());

//            if ("200".equals(baos.toString())){
            if (response.getStatusLine().getStatusCode()==200){
            	a=1l;
            }
            else{
            	a=0l;
            }

        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }

///////////////////////////////////////////////////////////////////////////////////////////////////
//        HttpClient httpClient = new DefaultHttpClient();
//        String url = "http://192.168.43.216:8080/PicknRide/rest/login/register?name="+data[0]+"&pwd="+data[1];
//
//        try {
//            HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
//            String b;
//            if ((b=httpResponse.getEntity().getContent().toString()).equals("200")) {
//                a = 1l;
//                Log.d("Http:",b);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return a;
    }

    //private EditText findViewById(int useridedittext) {
		// TODO Auto-generated method stub
		//return null;
	//}

	protected void onProgressUpdate(Integer... progress) {
        
    }

    protected void onPostExecute(Long result) {
        
    }
}
