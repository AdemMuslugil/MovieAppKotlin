package com.adem.movieappkotlin.service

import com.adem.movieappkotlin.model.MovieModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    val BASE_URL = "https://api.themoviedb.org/3/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)



    fun getData(page : String) : Single<MovieModel>{
        return api.getMovie(page)
    }

}