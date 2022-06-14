package com.adem.movieappkotlin.service

import com.adem.movieappkotlin.Util.API_KEY
import com.adem.movieappkotlin.model.MovieModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/top_rated")
    fun getMovie(
        @Query("page") page : String,
        @Query("api_key") api_key: String = API_KEY) : Single<MovieModel>
}