package com.bcafinance.omdb.service

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.bcafinance.omdb.model.OMDBResponse

interface OMDBApiInterface {
    @GET("?apikey=80641bfb")
    fun getMovie(@Query("s") query: String):
            retrofit2.Call<OMDBResponse>
}