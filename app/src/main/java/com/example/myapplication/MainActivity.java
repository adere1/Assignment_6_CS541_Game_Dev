package com.example.myapplication;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    JSONParser jParser = new JSONParser();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button button = (Button) findViewById(R.id.button1);
        StrictMode.setThreadPolicy(policy);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                String result = null;
                InputStream is = null;
                EditText editText = (EditText)findViewById(R.id.e1);
                String v1 = editText.getText().toString();
                EditText editText1 = (EditText)findViewById(R.id.e2);
                EditText editText2 = (EditText)findViewById(R.id.e3);
                String url = "http://149.125.136.144/selectdata.php";

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
                    urlConnection = (HttpURLConnection) currentUrl.openConnection();
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    while ((inputStr = streamReader.readLine()) != null) {
                        responseStrBuilder.append(inputStr);
                    }
                    //editText2.setText(responseStrBuilder.toString());
                    JSONObject no = new JSONObject(responseStrBuilder.toString());
                    JSONObject no1 = new JSONObject(no.getString("0"));
                    editText2.setText(no1.getString("notedata"));
                }catch(Exception e){
                   Log.e("log_tag", "Error in http connection "+e.toString());
                   Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
