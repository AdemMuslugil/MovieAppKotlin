package com.adem.movieappkotlin.Util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.adem.movieappkotlin.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadImage(url: String,progressBar : CircularProgressDrawable){

    val option = RequestOptions()
        .placeholder(progressBar)
        .error(R.mipmap.ic_launcher)



    Glide
        .with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)

}


fun placeHolderProgressBar(context: Context) : CircularProgressDrawable{

    return CircularProgressDrawable(context).apply {
        strokeWidth = 3f
        centerRadius = 25f
        start()
        }

}