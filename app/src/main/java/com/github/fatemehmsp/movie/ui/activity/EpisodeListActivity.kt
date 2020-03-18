package com.github.fatemehmsp.movie.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.R
import com.github.fatemehmsp.movie.databinding.ActivityEpisodeListBinding
import com.github.fatemehmsp.movie.di.EpisodeList.DaggerEpisodeListActivityComponent
import com.github.fatemehmsp.movie.ui.adapter.EpisodeListAdapter
import com.github.fatemehmsp.movie.viewmodel.EpisodeListViewModel
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar_main.*
import javax.inject.Inject

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListActivity : AppCompatActivity() {

    private var seasonId = 0
    private var seasonTitle = ""

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {

        private const val SEASON_ID_EXTRA = "seasonIdExtra"
        private const val SEASON_TITLE_EXTRA = "seasonTitleExtra"

        fun createIntent(context: Context, seasonId: Int, seasonTitle: String): Intent {
            val intent = Intent(context, EpisodeListActivity::class.java)
            intent.putExtra(SEASON_ID_EXTRA, seasonId)
            intent.putExtra(SEASON_TITLE_EXTRA, seasonTitle)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        processIntent()

        setupDagger()

        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(EpisodeListViewModel::class.java)

        val binding: ActivityEpisodeListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_episode_list)
        binding.episodeListVM = viewModel
        binding.lifecycleOwner = this

        initToolbar()

        viewModel.getSeasonEpisodes(seasonId)

        viewModel.episodes.observe(this, Observer {
            binding.episodeList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.episodeList.adapter =
                EpisodeListAdapter(this, it)
        })
    }

    private fun processIntent() {
        seasonId = intent.extras.getInt(SEASON_ID_EXTRA)
        seasonTitle = intent.extras.getString(SEASON_TITLE_EXTRA)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbarMain)
        toolbarMain.setContentInsetsAbsolute(0, 0)
        toolbarTitle.text = seasonTitle
        toolbarBack.visibility = View.VISIBLE
        toolbarBack.setOnClickListener { finish() }
    }

    private fun setupDagger() {

        val episodeListActivityComponent = DaggerEpisodeListActivityComponent
            .builder()
            .applicationComponent((application as App).getApplicationComponent())
            .build()

        episodeListActivityComponent.inject(this)
    }
}