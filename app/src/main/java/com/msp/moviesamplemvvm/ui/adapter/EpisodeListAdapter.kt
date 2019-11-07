package com.msp.moviesamplemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.msp.moviesamplemvvm.BR
import com.msp.moviesamplemvvm.databinding.RowEpisodeListBinding
import com.msp.moviesamplemvvm.model.SeasonModel
import com.msp.moviesamplemvvm.util.DataBindingViewHolder
import kotlinx.android.synthetic.main.row_episode_list.view.*

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListAdapter(private val items: MutableList<SeasonModel>) :
    RecyclerView.Adapter<EpisodeListAdapter.EpisodeHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder =
        EpisodeHolder(
            RowEpisodeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.onBind(items[position])
        holder.dataBinding.root.episodeParent.setOnClickListener {}
    }

    inner class EpisodeHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<SeasonModel>(dataBinding) {
        override fun onBind(t: SeasonModel): Unit = with(t) {
            dataBinding.setVariable(BR.season, t)
            dataBinding.executePendingBindings()
        }

    }
}