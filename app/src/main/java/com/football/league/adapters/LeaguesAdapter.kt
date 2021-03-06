package com.football.league.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.football.league.Model.responses.Competition
import com.football.league.R
import com.football.league.classes.Constants
import com.football.league.classes.GlobalFunctions
import com.football.league.databinding.ItemLeagueBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.football.league.database.room.AppDatabase
import com.football.league.database.room.entities.Favorite

class LeaguesAdapter(
    private val context: Context,
    private val list: List<Competition>,
    private val tag: String?,
    private val listener: OnItemClickListener,
) : RecyclerView.Adapter<LeaguesAdapter.MyViewHolder>() {

    private var binding: ItemLeagueBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (tag.equals(Constants.FAVORITE_FRAGMENT))
            holder.binding.favorite.setImageResource(R.drawable.star_sel)

        GlobalFunctions.setImage(
            context,
            list[position]?.emblemUrl,
            holder.binding.logo,
            R.drawable.list_noimg
        )

        holder.binding.longName.text = getName(position)

        if (list[position]?.shortName != null)
            holder.binding.shortName.text =
                "(" + list[position]?.shortName + ")"
        else
            holder.binding.shortName.visibility = ViewGroup.INVISIBLE

        if (list[position]?.numberOfAvailableSeasons != null)
            holder.binding.availableSeasons.text =
                context.getString(R.string.available_seasons) + " " + list[position]?.numberOfAvailableSeasons
        else
            holder.binding.availableSeasons.visibility = ViewGroup.INVISIBLE

        if (list[position]?.plan != null)
            holder.binding.plan.text =
                context.getString(R.string.plan) + " " + list[position]?.plan
        else
            holder.binding.plan.visibility = ViewGroup.INVISIBLE

        clicks(holder, position)
    }

    private fun getName(position: Int): String? {
        var name: String? = list[position]?.name
        if (name?.length!! > 15)
            name = name.substring(0, 15) + "..."
        return name
    }


    private fun clicks(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.leagueClick(position)
        }

        holder.binding.favorite.setOnClickListener {
            addToFavorite(position, holder)
        }
    }

    private fun addToFavorite(position: Int, holder: MyViewHolder) {
        Observable.fromCallable {
            AppDatabase.getDatabase(context = context)?.favoriteDao()?.insert(
                Favorite(
                    id = list[position]?.id,
                    competition = list[position],
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { holder.binding.favorite.setImageResource(R.drawable.star_sel) }
    }

    interface OnItemClickListener {
        fun leagueClick(position: Int)
    }

    class MyViewHolder(itemView: ItemLeagueBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemLeagueBinding = itemView

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
}