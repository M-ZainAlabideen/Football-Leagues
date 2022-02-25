package com.football.league.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.football.league.databinding.FragmentLeagueDetailsBinding

class LeagueDetailsFragment : Fragment() {
    private var binding: FragmentLeagueDetailsBinding? = null

    companion object {
        var activity: FragmentActivity? = null
        var fragment: LeagueDetailsFragment? = null

        fun newInstance(activity: FragmentActivity?): LeagueDetailsFragment? {
            LeagueDetailsFragment.activity = activity
            if (fragment == null)
                fragment = LeagueDetailsFragment()
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
        initVisibility()
        initViewModel()
    }

    //initialize activity
    private fun initActivity() {
        if (LeagueDetailsFragment.activity == null)
            LeagueDetailsFragment.activity = activity
    }

    //initialize visibility of Views after Fragment Created
    private fun initVisibility() {
    }

    private fun initViewModel() {
    }

}