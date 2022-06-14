package com.adem.movieappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.adem.movieappkotlin.R
import com.adem.movieappkotlin.adapter.MovieAdapter
import com.adem.movieappkotlin.databinding.FragmentHomeBinding
import com.adem.movieappkotlin.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel : HomeViewModel
    private val adapter = MovieAdapter(arrayListOf())
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.refreshData(page.toString())

        binding.MovieList.layoutManager = GridLayoutManager(context,2)
        binding.MovieList.adapter = adapter

        observeLiveData()
        swipeRefresh(page.toString())
        pageChange()
    }


    fun observeLiveData(){

        viewModel.movies.observe(viewLifecycleOwner, Observer {

            it?.let {
                binding.MovieList.visibility = View.VISIBLE
                adapter.updateMovieList(it.results!!)
                viewModel.movieLoading.value = false
                viewModel.movieError.value = false
            }
        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it){
                    binding.MovieList.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                else {
                    binding.errorTextView.visibility = View.GONE
                }
            }
        })


        viewModel.movieLoading.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it){
                    binding.MovieList.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                else{
                    binding.progressBar.visibility = View.GONE
                }
            }

        })

    }


    private fun swipeRefresh(page : String){

        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.MovieList.visibility = View.GONE
            binding.errorTextView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            viewModel.refreshData(page)
            binding.swipeRefreshLayout.isRefreshing = false

        }

    }


    fun pageChange(){

        binding.nextButton.setOnClickListener {
            page += 1

            if (viewModel.movies.value!!.totalPages!!.toInt() > page){

                viewModel.refreshData(page.toString())
                swipeRefresh(page.toString())
                binding.pageText.setText(page.toString())

            }else{
                page =viewModel.movies.value!!.totalPages!!.toInt() - 1
                Toast.makeText(binding.root.context, "Last Page", Toast.LENGTH_SHORT).show()
            }
            

        }

        binding.backButton.setOnClickListener {
            page -= 1
            
            if (page > 0){
                
                viewModel.refreshData(page.toString())
                swipeRefresh(page.toString())
                binding.pageText.setText(page.toString())
            }
            else{
                page = 1
                Toast.makeText(binding.root.context, "First Page", Toast.LENGTH_SHORT).show()
            }
            

        }


    }


}