package com.example.flixtersplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixtersplus.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rv = binding.rv
        val layoutManager = LinearLayoutManager(this)
        rv.layoutManager = layoutManager

        update(rv)
    }

    private fun update(rv: RecyclerView) {

        val client = AsyncHttpClient()
        val params = RequestParams()

        client["https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed" , params , object :
        JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                Log.i("MainActivity" , "on response success : ${json?.jsonObject.toString()}")

                val resultJson  = json?.jsonObject?.get("results").toString()
                Log.i("MainActiviy" , resultJson.toString())

                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movies>>(){}.type

                val list : List<Movies> = gson.fromJson(resultJson , arrayMovieType)
                Log.i("MainActivity" , list[2].toString())

                val adapter = MovieAdapter(this@MainActivity , list)
                rv.adapter = adapter
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.i("MainActivity" , "error while fetching data : $response")
            }
        }]
    }
}