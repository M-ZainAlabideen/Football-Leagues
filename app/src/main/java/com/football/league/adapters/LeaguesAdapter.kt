package com.football.league.adapters

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.football.league.Model.responses.Competition
import com.football.league.Model.responses.GetLeagues
import com.football.league.R
import com.football.league.classes.GlobalFunctions
import com.football.league.databinding.ItemLeagueBinding


class LeaguesAdapter(
    private val context: Context,
    private val list: List<Competition>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LeaguesAdapter.MyViewHolder>() {

    private var binding: ItemLeagueBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        GlobalFunctions.setImage(
            context,
            "https://tse1.mm.bing.net/th?id=OIP.neTp253amaORNv-lj2e9-AHaE8&pid=Api&P=0&w=244&h=163",
            holder.binding.logo,
            R.drawable.list_noimg
        )


        //GlobalFunctions.setImage(context,getLeagues?.competitions?.get(position)?.emblemUrl,holder.binding.logo,R.drawable.list_noimg)


        var name :String? = list.get(position)?.name
        if (name?.length!! > 20)
            name = name.substring(0, 15) + "..."
        holder.binding.longName.text = name
        if (list.get(position)?.code != null)
            holder.binding.shortName.text =
                "(" + list.get(position)?.code + ")"
        else
            holder.binding.shortName.visibility = ViewGroup.INVISIBLE

        if (list.get(position)?.numberOfAvailableSeasons != null)
            holder.binding.availableSeasons.text =
                context.getString(R.string.available_seasons) + " " + list.get(
                    position
                )?.numberOfAvailableSeasons
        else
            holder.binding.availableSeasons.visibility = ViewGroup.INVISIBLE

        if (list.get(position)?.plan != null)
            holder.binding.plan.text =
                context.getString(R.string.plan) + " " + list.get(
                    position
                )?.plan
        else
            holder.binding.plan.visibility = ViewGroup.INVISIBLE

        itemCLick(holder, position)
    }

    private fun itemCLick(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.leagueClick(position)
        }

        holder.binding.favorite.setOnClickListener{
            listener.favoriteClick(position,holder)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun leagueClick(position: Int)

        fun favoriteClick(position: Int,holder: MyViewHolder)
    }


    class MyViewHolder(itemView: ItemLeagueBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemLeagueBinding = itemView

    }

}