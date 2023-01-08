package com.bcafinance.omdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcafinance.omdb.adapter.OMDBAdapter
import com.bcafinance.omdb.adapter.OMDBViewHolder
import com.bcafinance.omdb.model.OMDBResponse
import com.bcafinance.omdb.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkConfig().getService()
            .getMovie(txtSearch.text.toString())
            .enqueue(object : retrofit2.Callback<OMDBResponse> {
                override fun onFailure(call: Call<OMDBResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<OMDBResponse>,
                    response: Response<OMDBResponse>
                ) {
                    Log.d("Response", response.toString())

                    var listMovie = response.body()?.search!!

                    val movieAdapter = ArrayList(listMovie).let { OMDBAdapter(it) }
                    lstMovie.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = movieAdapter

                    }
                }
            })
    }

}