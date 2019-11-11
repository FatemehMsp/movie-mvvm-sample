package com.github.fatemehmsp.movie.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fatemehmsp.movie.R
import com.github.fatemehmsp.movie.databinding.ActivityMainBinding
import com.github.fatemehmsp.movie.ui.adapter.SeasonListAdapter
import com.github.fatemehmsp.movie.viewmodel.MainViewModel



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainVM = viewModel
        binding.lifecycleOwner = this

        viewModel.movieData.observe(this, Observer {
            binding.mainMovieSeasonList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.mainMovieSeasonList.itemAnimator = DefaultItemAnimator()
            binding.mainMovieSeasonList.isNestedScrollingEnabled = false
            binding.mainMovieSeasonList.adapter = SeasonListAdapter(this, it.seasonList)
        })

        viewModel.message.observe(this, Observer {
            Toast.makeText(this, getString(it), Toast.LENGTH_LONG).show()
        })
    }
}
