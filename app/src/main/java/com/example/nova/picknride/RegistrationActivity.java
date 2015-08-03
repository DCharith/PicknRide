package com.example.nova.picknride;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBoxDriverMode);
        final LinearLayout collapsibleLayout = (LinearLayout)findViewById(R.id.collapsible);
        collapsibleLayout.setVisibility(View.GONE);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) { //b=checked
                if (b)
                    collapsibleLayout.setVisibility(View.VISIBLE);

                else
                    collapsibleLayout.setVisibility(View.GONE);
            }
        });

        Button regButton = (Button)findViewById(R.id.registerButton2);
        final EditText nameField = (EditText)findViewById(R.id.nameField);
        final EditText nicField = (EditText)findViewById(R.id.nicField);
        final EditText genderField = (EditText)findViewById(R.id.genderField);
        final EditText ageField = (EditText)findViewById(R.id.ageField);
        final EditText uIDField = (EditText)findViewById(R.id.phoneNumberField);
        final EditText passwordField = (EditText)findViewById(R.id.passwordField);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long a=0;
               try {
                   String name = nameField.getText().toString();
                   String nic = nicField.getText().toString();
                   String gender = genderField.getText().toString();
                   String age = ageField.getText().toString();
                   String uID = uIDField.getText().toString();
                   String password = passwordField.getText().toString();
                   GetRegistrationData getData = new GetRegistrationData();
                   a = getData.execute(name, nic, gender, age, uID, password).get();
                   Log.d("AAAAAAAAAAAAAAAAA:", String.valueOf(a));
               }
               catch (Exception e){
                    e.printStackTrace();
               }
               if (a==1L) {
                   Intent i = new Intent(RegistrationActivity.this, WelcomeActivity.class);
                   startActivity(i);
               }
               else
                   Toast.makeText(getApplicationContext(), "Invalid Data!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class GetRegistrationData extends AsyncTask<String, Integer, Long>{

    @Override
    protected Long doInBackground(String... data) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.1.2:12598/RESTMaven3/messages");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        JSONObject object = new JSONObject();
        try {
            object.put("author", "Charith");
            object.put("created", "2015-07-31T12:42:18.169+05:30");
            object.put("text", "Charith");
//            object.put("name", data[0]);
//            object.put("nic", data[1]);
//            object.put("gender", data[2]);
//            object.put("age", data[3]);
//            object.put("uID", data[4]);
//            object.put("password", data[5]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            //UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);
            //httpPost.setEntity(urlEncodedFormEntity);
            StringEntity se = new StringEntity(object.toString());
            httpPost.setEntity(se);
            Log.d("SETENTITY:", "Entity SET!!!!!!!!!!!!!!!");

            HttpResponse response = httpClient.execute(httpPost);
            Log.d("RESPONSE:", String.valueOf(response.getStatusLine().getStatusCode()));
            Log.d("RESPONSE:", response.toString());
            if(response.getStatusLine().getStatusCode()==200)
                return 1L;
        }
         catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();

        }

        return 0L;
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    class GetData extends AsyncTask<String, Integer, Long> {
//        protected Long doInBackground(String... data) {
//
//            long a=0;
//            HttpClient httpClient = new DefaultHttpClient();
//            // Creating HTTP Post
//            HttpPost httpPost = new HttpPost("http://192.168.43.146:8080/PicknRide/webapi/user/register/");
//
//            Log.d("httpPost", "HttpPost ok");
//            // Building post parameters
//            // key and value pair
//
//            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
//            nameValuePair.add(new BasicNameValuePair("name", data[0]));
//            nameValuePair.add(new BasicNameValuePair("NIC", data[1]));
//            nameValuePair.add(new BasicNameValuePair("gender", data[2]));
//            nameValuePair.add(new BasicNameValuePair("age", data[3]));
//            nameValuePair.add(new BasicNameValuePair("uID", data[4]));
//            nameValuePair.add(new BasicNameValuePair("password", data[5]));
//
//
//
//            // Url Encoding the POST parameters
//            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
//                Log.d("Entity", "SET");
//
//            } catch (UnsupportedEncodingException e) {
//                // writing error to Log
//                e.printStackTrace();
//            }
//
//            // Making HTTP Request
//            try {
//                HttpResponse response = httpClient.execute(httpPost);
//
//                // writing response to log
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                response.getEntity().writeTo(baos);
//                Log.d("Http:", baos.toString());
//
////            if ("200".equals(baos.toString())){
//                if (response.getStatusLine().getStatusCode()==200){
////                Log.d("Response Code", response.getStatusLine().getStatusCode().toString());
//                    a=1l;
//                }
//                else{
//                    a=0l;
//                }
//
//            } catch (ClientProtocolException e) {
//                // writing exception to log
//                e.printStackTrace();
//            } catch (IOException e) {
//                // writing exception to log
//                e.printStackTrace();
//
//            }
//
//            return a;
//
//
//
////            String url = "http://192.168.43.146:8080/PicknRide/webapi/user/3";
////            InputStream inputStream = null;
////            String result = "";
////            try {
////
////                // create HttpClient
////                HttpClient httpclient = new DefaultHttpClient();
////
////                // make GET request to the given URL
////                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
////
////                // receive response as inputStream
////                inputStream = httpResponse.getEntity().getContent();
////
////                // convert inputstream to string
////                if(inputStream != null)
////                    result = convertInputStreamToString(inputStream);
////                else {
////                    result = "Did not work!";
////                    return 0L;
////                }
////            } catch (Exception e) {
////                Log.d("InputStream", e.getLocalizedMessage());
////            }
////            Log.d("Response: ", result);
////            return 1L;
//        }
//
//        private String convertInputStreamToString(InputStream inputStream) throws IOException{
//            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//            String line = "";
//            String result = "";
//            while((line = bufferedReader.readLine()) != null)
//                result += line;
//
//            inputStream.close();
//            return result;
//
//        }
//        //private EditText findViewById(int useridedittext) {
//        // TODO Auto-generated method stub
//        //return null;
//        //}
//
//        protected void onProgressUpdate(Integer... progress) {
//
//        }
//
//        protected void onPostExecute(Long result) {
//
//        }
//    }




}
