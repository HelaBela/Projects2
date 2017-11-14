package com.helenafranczak.movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.helenafranczak.movies.NetworkUtils.POPULAR_URL;
import static com.helenafranczak.movies.NetworkUtils.RATED_URL;

public class MainActivity extends AppCompatActivity {

    TextView text;

    GridView gridView;

    ArrayAdapter<Movie> myAdapter;

    ArrayList<Movie> moviesList;
    URL url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     text = (TextView) findViewById(R.id.text);

      gridView = (GridView)findViewById(R.id.grid);

      moviesList = new ArrayList<Movie>();

        url = NetworkUtils.buildUrl("popular");


        new MoviesQuery().execute(url);


    }

    public class MoviesQuery extends AsyncTask<URL, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            URL movieUrl = urls[0];
            String result = "";


            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieUrl);

                ArrayList<Movie> simpleJsonMovieResponse = JSONparsing.getStringsFromJson(MainActivity.this, jsonMovieResponse);

                return simpleJsonMovieResponse;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result) {

            myAdapter = new Adapter(MainActivity.this, 0 , result);


            gridView.setAdapter(myAdapter);

            if(result!=null){


                myAdapter.addAll(result);
                myAdapter.notifyDataSetChanged();

                //Log.e("myArray", String.valueOf(moviesArray));

            }

    }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        URL mUrl;

        int menuItemThatWasSelected= item.getItemId();
        if(menuItemThatWasSelected==R.id.action_popularity){

            mUrl  = NetworkUtils.buildUrl("popular");

            new MoviesQuery().execute(mUrl);


        }

        if(menuItemThatWasSelected==R.id.action_rating){



            mUrl = NetworkUtils.buildUrl("rated");

            new MoviesQuery().execute(mUrl);
        }

        return super.onOptionsItemSelected(item);
    }
}





