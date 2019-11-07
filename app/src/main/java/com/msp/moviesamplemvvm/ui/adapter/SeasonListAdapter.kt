package com.msp.moviesamplemvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.msp.moviesamplemvvm.BR
import com.msp.moviesamplemvvm.databinding.RowSeasonListBinding
import com.msp.moviesamplemvvm.model.SeasonModel
import com.msp.moviesamplemvvm.ui.activity.EpisodeListActivity
import com.msp.moviesamplemvvm.util.DataBindingViewHolder
import kotlinx.android.synthetic.main.row_season_list.view.*

/**
 * Created by Fatemeh Movassaghpour on 11/6/2019.
 */
class SeasonListAdapter(val context: Context, private val items: MutableList<SeasonModel>) :
    RecyclerView.Adapter<SeasonListAdapter.SeasonHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonHolder =
        SeasonHolder(
            RowSeasonListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SeasonHolder, position: Int) {
        holder.onBind(items[position])
        holder.dataBinding.root.seasonParent.setOnClickListener {
            context.startActivity(
                EpisodeListActivity
                    .createIntent(context, items[position].id, items[position].title)
            )
        }
    }

    inner class SeasonHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<SeasonModel>(dataBinding) {
        override fun onBind(t: SeasonModel): Unit = with(t) {
            dataBinding.setVariable(BR.season, t)
            dataBinding.executePendingBindings()
        }

    }
}