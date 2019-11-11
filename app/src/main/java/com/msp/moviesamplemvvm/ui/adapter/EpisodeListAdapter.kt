package com.msp.moviesamplemvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.msp.moviesamplemvvm.BR
import com.msp.moviesamplemvvm.databinding.RowEpisodeListBinding
import com.msp.moviesamplemvvm.model.EpisodeModel
import com.msp.moviesamplemvvm.ui.activity.EpisodeDetailActivity
import com.msp.moviesamplemvvm.util.DataBindingViewHolder
import kotlinx.android.synthetic.main.row_episode_list.view.*

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListAdapter(val context: Context, private val items: MutableList<EpisodeModel>) :
    RecyclerView.Adapter<EpisodeListAdapter.EpisodeHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder =
        EpisodeHolder(
            RowEpisodeListBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.onBind(items[position])
        holder.dataBinding.root.episodeParent.setOnClickListener {
            context.startActivity(
                EpisodeDetailActivity.createIntent(
                    context, items[position].season, items[position].episode!!,
                    items[position].title!!
                )
            )
        }
    }

    inner class EpisodeHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<EpisodeModel>(dataBinding) {
        override fun onBind(t: EpisodeModel): Unit = with(t) {
            dataBinding.setVariable(BR.episode, t)
            dataBinding.executePendingBindings()
        }

    }
}