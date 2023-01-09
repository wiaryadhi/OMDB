package com.bcafinance.omdb

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcafinance.omdb.adapter.OMDBAdapter
import com.bcafinance.omdb.adapter.OMDBViewHolder
import com.bcafinance.omdb.model.OMDBResponse
import com.bcafinance.omdb.model.SearchItem
import com.bcafinance.omdb.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener(View.OnClickListener {

            (txtSearch.text.toString())
        })


    }


    fun searchMovie(title: String) {

        NetworkConfig().getService()
            .getMovie(title)
            .enqueue(object : retrofit2.Callback<OMDBResponse> {


                override fun onResponse(
                    call: Call<OMDBResponse>,
                    response: Response<OMDBResponse>
                ) {
                    Log.d("Response OMDB API Search", response.toString())
                    if (response.body()?.search != null) {
                        setupList(
                            response.body()?.search as List<SearchItem>
                        )

                        var listMovie = response.body()?.search!!

                        val movieAdapter = ArrayList(listMovie).let { OMDBAdapter(it) }
                        lstMovie.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = movieAdapter
                        }

                    } else {
                        Toast.makeText(this@MainActivity,"Data yang dimasukkan tidak valid",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<OMDBResponse>, t: Throwable) {

                    Log.d("Failed Response", t.message.toString())
                }


            })
    }

    fun setupList(list: List<SearchItem>) {

    }
}