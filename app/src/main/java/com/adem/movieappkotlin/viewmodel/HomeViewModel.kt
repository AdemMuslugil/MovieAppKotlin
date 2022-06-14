package com.adem.movieappkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adem.movieappkotlin.model.MovieModel
import com.adem.movieappkotlin.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val movieAPIService = MovieAPIService()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<MovieModel>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()


    fun refreshData(page : String){
        getDataFromAPI(page)
    }


    fun getDataFromAPI(page : String){
        movieLoading.value = true

        disposable.add(
            movieAPIService.getData(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieModel>(){
                    override fun onSuccess(t: MovieModel) {
                        movies.value = t
                    }

                    override fun onError(e: Throwable) {
                        println(e.message)
                        movieError.value = true
                        movieLoading.value = false
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}