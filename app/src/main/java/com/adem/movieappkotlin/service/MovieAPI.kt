package com.adem.movieappkotlin.service

import com.adem.movieappkotlin.Util.API_KEY
import com.adem.movieappkotlin.model.MovieModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://api.themoviedb.org/3/movie/top_rated?api_key=6b9344e9ba1ce6a689f57a61c0b4450f&language=en-US&page=1

    @GET("movie/top_rated")
    fun getMovie(
        @Query("page") page : String,
        @Query("api_key") api_key: String = API_KEY) : Single<MovieModel>
}