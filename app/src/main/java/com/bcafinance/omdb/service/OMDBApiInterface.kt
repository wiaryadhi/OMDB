package com.bcafinance.omdb.service

import retrofit2.http.GET
import retrofit2.http.Query
import com.bcafinance.omdb.model.OMDBResponse
import retrofit2.Call
import retrofit2.http.Path

interface OMDBApiInterface {

    //http://www.omdbapi.com/?apikey=e7150c4e&s=Avatar

    @GET("?apikey=e7150c4e")
    fun getMovie(@Query("s") query: String):
            Call<OMDBResponse>

    @GET("?apikey=e7150c4e&s={title}")
    fun getMovie2(@Path("title") query: String):
            Call<OMDBResponse>
}