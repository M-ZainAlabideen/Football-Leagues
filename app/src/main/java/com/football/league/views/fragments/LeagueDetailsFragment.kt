package com.football.league.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.football.league.Model.responses.Competition
import com.football.league.R
import com.football.league.classes.GlobalFunctions
import com.football.league.databinding.FragmentLeagueDetailsBinding

class LeagueDetailsFragment : Fragment() {
    private var binding: FragmentLeagueDetailsBinding? = null
    var competition: Competition? = null

    companion object {
        var activity: FragmentActivity? = null
        var fragment: LeagueDetailsFragment? = null

        fun newInstance(
            activity: FragmentActivity?,
            competition: Competition
        ): LeagueDetailsFragment? {
            LeagueDetailsFragment.activity = activity
            if (fragment == null)
                fragment = LeagueDetailsFragment()

            fragment?.competition = competition
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeagueDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActivity()
        setData()
    }

    private fun initActivity() {
        if (LeagueDetailsFragment.activity == null)
            LeagueDetailsFragment.activity = activity
    }

    private fun setData() {
        GlobalFunctions.setImage(
            requireContext(),
            competition!!.emblemUrl,
            binding!!.logo,
            R.drawable.league_details_noimg
        )

        binding?.longName?.text = getName()

        if (competition?.shortName != null)
            binding?.shortName?.text =
                "(" + competition?.shortName + ")"
        else
            binding?.shortName?.visibility = ViewGroup.INVISIBLE

        if (competition?.numberOfAvailableSeasons != null)
            binding?.availableSeasons?.text =
                getString(R.string.available_seasons) + " " + competition?.numberOfAvailableSeasons
        else
            binding?.availableSeasons?.visibility = ViewGroup.INVISIBLE

        if (competition?.plan != null)
            binding?.plan?.text =
                getString(R.string.plan) + " " + competition?.plan
        else
            binding?.plan?.visibility = ViewGroup.INVISIBLE

    }

    private fun getName():String?{
        var name: String? = competition?.name
        if (name?.length!! > 20)
            name = name.substring(0, 15) + "..."
        return name
    }
}