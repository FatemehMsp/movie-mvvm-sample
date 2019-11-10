package com.msp.moviesamplemvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.msp.moviesamplemvvm.R
import com.msp.moviesamplemvvm.databinding.ActivityEpisodeDetailBinding
import com.msp.moviesamplemvvm.viewmodel.EpisodeDetailViewModel
import com.msp.moviesamplemvvm.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar_main.*

/**
 * Created by Fatemeh Movassaghpour on 11/10/2019.
 */
class EpisodeDetailActivity : AppCompatActivity() {

    private var seasonId = 0
    private var episode = ""
    private var episodeTitle = ""

    companion object {

        private const val SEASON_ID_EXTRA = "seasonIdExtra"
        private const val EPISODE_EXTRA = "episodeExtra"
        private const val EPISODE_TITLE_EXTRA = "episodeTitleExtra"

        fun createIntent(
            context: Context,
            seasonId: Int,
            episode: String,
            episodeTitle: String
        ): Intent {
            val intent = Intent(context, EpisodeDetailActivity::class.java)
            intent.putExtra(SEASON_ID_EXTRA, seasonId)
            intent.putExtra(EPISODE_EXTRA, episode)
            intent.putExtra(EPISODE_TITLE_EXTRA, episodeTitle)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        processIntent()

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(seasonId, episode)
        ).get(EpisodeDetailViewModel::class.java)

        val binding: ActivityEpisodeDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_episode_detail)
        binding.episodeDetailVM = viewModel
        binding.lifecycleOwner = this

        initToolbar()

    }

    private fun processIntent() {
        seasonId = intent.extras.getInt(SEASON_ID_EXTRA)
        episode = intent.extras.getString(EPISODE_EXTRA)
        episodeTitle = intent.extras.getString(EPISODE_TITLE_EXTRA)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbarMain)
        toolbarMain.setContentInsetsAbsolute(0, 0)
        toolbarTitle.text = episodeTitle
        toolbarBack.visibility = View.VISIBLE
        toolbarBack.setOnClickListener { finish() }
    }

}