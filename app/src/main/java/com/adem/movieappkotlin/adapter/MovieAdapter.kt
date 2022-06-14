package com.adem.movieappkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.adem.movieappkotlin.Util.downloadImage
import com.adem.movieappkotlin.Util.placeHolderProgressBar
import com.adem.movieappkotlin.databinding.ItemRowBinding
import com.adem.movieappkotlin.model.Result
import com.adem.movieappkotlin.view.HomeFragment
import com.adem.movieappkotlin.view.HomeFragmentDirections

class MovieAdapter(private val movieList : ArrayList<Result>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    class MovieHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.homeTitleText.text = movieList[position].title
        holder.binding.releaseDateTextView.text = movieList[position].releaseDate

        val url = "https://image.tmdb.org/t/p/w500${movieList[position].posterPath}"
        holder.binding.homeImageView.downloadImage(url,
            placeHolderProgressBar(holder.binding.root.context))

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    fun updateMovieList(newList : List<Result>){

        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()

    }
}