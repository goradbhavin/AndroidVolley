package com.gb.mysecondvolleydemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private RecyclerView recyclerView;
    private MovieListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listAdapter = new MovieListAdapter(movieList,this);
        recyclerView.setAdapter(listAdapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        RequestQueue queue = SingletonRequestQueue.getInstance(this).getRequestQueue();


        final JsonArrayRequest movieReq = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hidePDialog();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        JSONArray genreArray = obj.getJSONArray("genre");
                        List<String> genre = new ArrayList<String>();
                        for (int j = 0; j < genreArray.length(); j++) {
                            genre.add((String) genreArray.get(j));
                        }

                        Movie movie = new Movie(
                                obj.getString("title"),
                                obj.getString("image"),
                                obj.getInt("releaseYear"),
                                obj.getDouble("rating"),
                                genre
                        );
                        movieList.add(movie);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("MainActivity", "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                hidePDialog();
            }
        });
        queue.add(movieReq);

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
