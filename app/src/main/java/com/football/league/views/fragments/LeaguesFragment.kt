package com.football.league.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.football.league.Model.responses.Competition
import com.football.league.R
import com.football.league.adapters.LeaguesAdapter
import com.football.league.classes.GlobalFunctions
import com.football.league.classes.Navigator
import com.football.league.databinding.FragmentLeaguesBinding
import com.football.league.view_model.LeaguesViewModel
import com.football.league.views.activities.MainActivity


class LeaguesFragment : Fragment() {
    private var binding: FragmentLeaguesBinding? = null
    private var viewModel: LeaguesViewModel? = null
    private var list: ArrayList<Competition> = ArrayList<Competition>()

    companion object {
        var activity: FragmentActivity? = null
        var fragment: LeaguesFragment? = null

        fun newInstance(activity: FragmentActivity): LeaguesFragment? {
            LeaguesFragment.activity = activity
            if (fragment == null)
                fragment = LeaguesFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaguesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActivity()
        setup()
        initViewModel()
        checkData()
    }

    private fun initActivity() {
        if (LeaguesFragment.activity == null)
            LeaguesFragment.activity = activity
    }


    private fun setup() {
        MainActivity.binding!!.bottomAppBar.menu.findItem(R.id.leagues).isChecked = true
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LeaguesViewModel::class.java]
    }

    private fun checkData() {
        if (list.isEmpty())
            leaguesApi()
        else
            setLeagues()
    }

    private fun leaguesApi() {
        binding?.leagues?.showShimmer()
        GlobalFunctions.disableLayout(binding!!.container)
        viewModel?.leagues()
            ?.observe(requireActivity()) { responseResult ->
                binding?.leagues?.hideShimmer()
                GlobalFunctions.enableLayout(binding!!.container)
                if (responseResult?.competitions != null && responseResult.competitions.isNotEmpty()) {
                    list.addAll(responseResult.competitions)
                    setLeagues()
                }
            }
    }

    private fun setLeagues() {
        binding?.leagues?.layoutManager = GridLayoutManager(activity, 2)
        binding?.leagues?.adapter =
            LeaguesAdapter(
                requireActivity(),
                list,null,
                object : LeaguesAdapter.OnItemClickListener {
                    override fun leagueClick(position: Int) {
                        Navigator.loadFragment(
                            activity,
                            LeagueDetailsFragment.newInstance(activity,list[position]),
                            R.id.fragment_container,
                            true
                        )
                    }

                }
            )
    }

}