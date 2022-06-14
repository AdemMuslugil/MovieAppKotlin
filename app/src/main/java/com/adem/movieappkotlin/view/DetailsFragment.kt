package com.adem.movieappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adem.movieappkotlin.R
import com.adem.movieappkotlin.Util.downloadImage
import com.adem.movieappkotlin.Util.placeHolderProgressBar
import com.adem.movieappkotlin.databinding.FragmentDetailsBinding
import com.adem.movieappkotlin.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.combine


class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(container?.context),container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showItem()

    }

    private fun showItem(){

        arguments?.let {

            val overview= DetailsFragmentArgs.fromBundle(it).movie.overview
            val url = "https://image.tmdb.org/t/p/w500${DetailsFragmentArgs.fromBundle(it).movie.posterPath}"

            binding.overviewTextView.text = overview
            binding.titleTextView.text = DetailsFragmentArgs.fromBundle(it).movie.title
            binding.releaseDateTextView.text = DetailsFragmentArgs.fromBundle(it).movie.releaseDate
            binding.voteTextView.text = DetailsFragmentArgs.fromBundle(it).movie.voteAverage.toString()

            binding.ImageView.downloadImage(url, placeHolderProgressBar(binding.root.context))


        }

    }


}