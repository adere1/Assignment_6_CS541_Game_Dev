package com.example.myapplication;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity
{
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    JSONParser jParser = new JSONParser();
    String url = "http://192.168.1.179/selectdata.php";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button button = (Button) findViewById(R.id.button1);
        final Button button1 = (Button) findViewById(R.id.button2);
        final Button button2 = (Button) findViewById(R.id.button3);
        StrictMode.setThreadPolicy(policy);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                String result = null;
                InputStream is = null;
                EditText editText = (EditText)findViewById(R.id.e1);
                String v1 = editText.getText().toString();
                EditText editText1 = (EditText)findViewById(R.id.e2);
                EditText editText2 = (EditText)findViewById(R.id.e3);
                EditText editText4 = (EditText)findViewById(R.id.e4);
                editText1.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);
                editText4.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);

                URL currentUrl = null;
                try {
                    currentUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                }
                HttpURLConnection urlConnection = null;
                InputStream in;
                BufferedReader streamReader = null;
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                try {

                    String name = editText.getText().toString();
                    urlConnection = (HttpURLConnection) currentUrl.openConnection();
                    String data = URLEncoder.encode("name", "UTF-8")
                            + "=" + URLEncoder.encode(name, "UTF-8");

                    data += "&" + URLEncoder.encode("getreq", "UTF-8") + "="
                            + URLEncoder.encode("true", "UTF-8");
                    urlConnection.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    while ((inputStr = streamReader.readLine()) != null) {
                        responseStrBuilder.append(inputStr);
                    }
                    //editText2.setText(responseStrBuilder.toString().split(name)[1]);
                    try {
                        int length = responseStrBuilder.toString().length();
                        String abc = responseStrBuilder.toString().substring(name.length() + 1, length);
                        JSONObject no = new JSONObject(abc);

                        JSONObject no1 = new JSONObject(no.getString("0"));
                        editText2.setText(no1.getString("notedata"));
                        Log.i("MyData", editText2.getText().toString());
                        //Log.d(name,responseStrBuilder.toString());
                    }catch (Exception e){
                        editText2.setText("No Data Present");
                    }


                }catch(Exception e){
                   Log.e("log_tag", "Error in http connection "+e.toString());
                   Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.e1);
                EditText editText4 = (EditText)findViewById(R.id.e4);
                EditText editText2 = (EditText)findViewById(R.id.e2);
                EditText editText3 = (EditText)findViewById(R.id.e3);
                editText2.setVisibility(View.VISIBLE);
                editText3.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                editText4.setVisibility(View.VISIBLE);
                editText4.setText(editText3.getText().toString());
                button2.setVisibility(View.VISIBLE);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);


                EditText editText = (EditText)findViewById(R.id.e1);
                EditText editText4 = (EditText)findViewById(R.id.e4);
                EditText editText2 = (EditText)findViewById(R.id.e2);
                EditText editText3 = (EditText)findViewById(R.id.e3);
                editText2.setVisibility(View.INVISIBLE);
                editText3.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                editText4.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                //editText4.setText(editText3.getText().toString());




                URL currentUrl = null;
                try {
                    currentUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                }
                HttpURLConnection urlConnection = null;
                InputStream in;
                BufferedReader streamReader = null;
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                try {

                    String name = editText.getText().toString();
                    urlConnection = (HttpURLConnection) currentUrl.openConnection();
                    String data = URLEncoder.encode("name", "UTF-8")
                            + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("getreq", "UTF-8") + "="
                            + URLEncoder.encode("false", "UTF-8");
                    data += "&" + URLEncoder.encode("data", "UTF-8") + "="
                            + URLEncoder.encode(editText4.getText().toString(), "UTF-8");
                    urlConnection.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    Log.d(name,editText4.getText().toString());

                    in = new BufferedInputStream(urlConnection.getInputStream());
                    streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    while ((inputStr = streamReader.readLine()) != null) {
                        responseStrBuilder.append(inputStr);
                    }
                    //editText2.setText(responseStrBuilder.toString().split(name)[1]);

                    Log.d(name,responseStrBuilder.toString());



                }catch(Exception e){
                    Log.e("log_tag", "Error in http connection "+e.toString());
                    Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
