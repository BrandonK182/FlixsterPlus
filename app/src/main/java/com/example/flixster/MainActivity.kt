package com.example.flixster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMovies = findViewById<RecyclerView>(R.id.rv_movie)
        // DO API CALL HERE
        var movies : List<Movie> = arrayListOf()

        // Create adapter passing in the list of emails
        var adapter = FlixsterViewAdapter(movies)
        // Attach the adapter to the RecyclerView to populate items
        rvMovies.adapter = adapter
        Log.d("MovieFragment", "adapter assigned")
        // Set layout manager to position the items
        rvMovies.layoutManager = LinearLayoutManager(this)

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        // Using the client, perform the HTTP request
        client[
            "https://api.themoviedb.org/3/movie/now_playing?&api_key="+ API_KEY,
            object : JsonHttpResponseHandler()
            {
                /*
                 * The onSuccess function gets called when
                 * HTTP response status is "200 OK"
                 */
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    //TODO - Parse JSON into Models
                    val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                    val moviesRawJSON : String = resultsJSON.toString()

                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                    movies = gson.fromJson(moviesRawJSON, arrayMovieType)

                    // Look for this in Logcat:
                    Log.d("MovieFragment", "response successful")
                    adapter = FlixsterViewAdapter(movies)
                    rvMovies.adapter = adapter
                    adapter.notifyDataSetChanged()

                    adapter.setOnClickListener(object :
                        FlixsterViewAdapter.OnClickListener {
                        override fun onClick(position: Int, movie : Movie) {
                            Toast.makeText(this@MainActivity, "test: " + movie.title, Toast.LENGTH_LONG).show()
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra("movie", movie)
                            startActivity(intent)
                        }
                    })
                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("MovieFragment", errorResponse)
                    }
                }
            }]


    }
    fun onItemClick(item: Movie) {
        Toast.makeText(this@MainActivity, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}