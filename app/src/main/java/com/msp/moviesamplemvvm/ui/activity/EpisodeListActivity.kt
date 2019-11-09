package com.msp.moviesamplemvvm.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.msp.moviesamplemvvm.R
import com.msp.moviesamplemvvm.databinding.ActivityEpisodeListBinding
import com.msp.moviesamplemvvm.ui.adapter.EpisodeListAdapter
import com.msp.moviesamplemvvm.viewmodel.EpisodeListViewModel
import com.msp.moviesamplemvvm.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar_main.*

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListActivity : AppCompatActivity() {

    private var seasonId = 0
    private var seasonTitle = ""

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

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(seasonId)
        ).get(EpisodeListViewModel::class.java)
        val binding: ActivityEpisodeListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_episode_list)
        binding.episodeLiatVM = viewModel
        binding.lifecycleOwner = this

        initToolbar()

        viewModel.episodes.observe(this, Observer {
            binding.episodeList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.episodeList.adapter = EpisodeListAdapter(this, it)
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


}