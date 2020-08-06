package com.example.onetonteaching.howistheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

//    http://api.openweathermap.org/data/2.5/weather?q=city_name&appid=api_key
    String api = null;

    public void getData(View view) throws ExecutionException, InterruptedException, UnsupportedEncodingException {

        EditText cityNameText = (EditText) findViewById(R.id.cityName);
        String cityName = URLEncoder.encode(cityNameText.getText().toString(), "UTF-8");

        String myurl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + api;
//        Log.i("url", myurl);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(cityNameText.getWindowToken(), 0);

        WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher();
        weatherDataFetcher.execute(myurl);
//        Log.i("url", myurl);
//        Log.i("data", s.substring(4));


    }

    public class WeatherDataFetcher extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            URL url = null;
            String allinfo = null;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                int data = isr.read();
                while (data != -1){

                    allinfo += (char) data;
                    data = isr.read();

                }
                return allinfo;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                TextView resultview = (TextView) findViewById(R.id.info);
                String resultInfo, desc = "";
//
                JSONObject jsonObject = new JSONObject(s.substring(4));
                String weatherarr = jsonObject.getString("weather");
//            Log.i("weather", weatherarr);
                JSONArray jsonArray = new JSONArray(weatherarr);

                for (int i = 0; i < jsonArray.length(); i++)
                {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    desc = jsonObject1.getString("description");
                    break;

                }
//
                JSONObject mainobject = jsonObject.getJSONObject("main");
                String temp = String.format("%.2f", Float.parseFloat(mainobject.getString("temp")) / 10f);
                float pressure = Float.parseFloat(mainobject.getString("pressure"));
//                Log.i("temp", temp + "");
//                Log.i("pressure", pressure + "");

                JSONObject windObject = jsonObject.getJSONObject("wind");
                float wspeed = Float.parseFloat(windObject.getString("speed"));
//                Log.i("wspeed", wspeed + "");

                JSONObject cloudObject = jsonObject.getJSONObject("clouds");
                float cloud = Float.parseFloat(cloudObject.getString("all"));
//                Log.i("cloud", cloud + "");

                JSONObject sysObject = jsonObject.getJSONObject("sys");
                String country = sysObject.getString("country");
//                Log.i("country", country);
//
                resultInfo = "Country : " + country + " || " + "\r\n" + "Description : " + desc + " || " + "\r\n" + "Temperature : " + temp + " `C || " + "\r\n" + "Pressure : " + pressure + " hpa || " + "\r\n" + "Wind Speed : " + wspeed + " m/s || " + "\r\n" + "Cloud : " + cloud + " % || ";
                resultview.setText(resultInfo);
//
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = "0f9907f7a93680bcc6ce04df4eb46b96";

    }
}