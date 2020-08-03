package com.example.onetonteaching.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> images, names;
    String subsetnames[] = new String[4];
    String name = null;
    int tappedtextNo, start = 0, max = 99;
    ImageView img;
    Random rand;
    TextView first, second, third, fourth;

    public void generateImage() throws ExecutionException, InterruptedException {

        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap curImage = imageDownloader.execute(images.get(start)).get();
        img = findViewById(R.id.celebImage);
        img.setImageBitmap(curImage);
        int corPos = rand.nextInt(4);
        subsetnames[corPos] = names.get(start);

        for (int i = 0; i < 4; i++){

            int inCorResult = rand.nextInt(100);
            while (inCorResult == start)
                inCorResult = rand.nextInt(100);

            int inCorPos = rand.nextInt(4);
            while (inCorPos == corPos)
                inCorPos = rand.nextInt(4);

            subsetnames[inCorPos] = names.get(inCorResult);

        }
        first.setText(subsetnames[0]);
        second.setText(subsetnames[1]);
        third.setText(subsetnames[2]);
        fourth.setText(subsetnames[3]);

        if (start + 1 > max)
            start = 0;
        else
            start++;

    }

    public void checkImage(View view) throws ExecutionException, InterruptedException {

        tappedtextNo = Integer.parseInt(view.getTag().toString());
        if (names.get(start - 1) == subsetnames[tappedtextNo]){

            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(this, names.get(start - 1) + " wants to know your location :3", Toast.LENGTH_LONG).show();

        }
        generateImage();

    }




    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... images) {

            URL url = null;
            HttpURLConnection urlConnection = null;
            Bitmap myImage;

            try {

                url = new URL(images[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                myImage = BitmapFactory.decodeStream(is);
                return myImage;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;

        }

    }




    public class Scraper extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {

            URL url = null;
            HttpURLConnection urlConnection = null;
            String pagesrc = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                int data = isr.read();
                while(data != -1){

                    pagesrc += (char) data;
                    data = isr.read();

                }
                return pagesrc;

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = new ArrayList<String>();
        images = new ArrayList<String>();
        img = findViewById(R.id.celebImage);
        rand = new Random();
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

//        https://www.imdb.com/list/ls052283250/

        String url = "https://www.imdb.com/list/ls052283250/";
        Scraper scraper = new Scraper();
        try {

            String myhtml = scraper.execute(url).get();


            String splithtml[] = myhtml.split("<div class=\"header filmosearch\">");
            String mynewhtml = splithtml[1].split("<div class=\"footer filmosearch\">")[0];

            Pattern pattern = Pattern.compile("img alt=\"(.*?)\"");
//            Log.i("length", Integer.toString(myhtml.length()));
            Matcher matcher = pattern.matcher(mynewhtml);
            while(matcher.find()){

                names.add(matcher.group(1));

            }


            pattern = Pattern.compile("src=\"(.*?)\"");
            matcher = pattern.matcher(mynewhtml);
            while(matcher.find()){

                images.add(matcher.group(1));

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            generateImage();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}