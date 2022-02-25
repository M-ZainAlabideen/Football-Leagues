package com.football.league.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.football.league.Model.responses.Competition
import com.football.league.R
import com.football.league.adapters.LeaguesAdapter
import com.football.league.classes.Navigator
import com.football.league.databinding.FragmentLeaguesBinding
import com.football.league.room.AppDatabase
import com.football.league.views.activities.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteFragment : Fragment() {
    private var binding: FragmentLeaguesBinding? = null
    var list: List<Competition> = ArrayList<Competition>()

    companion object {
        var activity: FragmentActivity? = null
        var fragment: FavoriteFragment? = null

        fun newInstance(activity: FragmentActivity): FavoriteFragment? {
            FavoriteFragment.activity = activity
            if (fragment == null)
                fragment = FavoriteFragment()
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
        initVisibility()
        setup()
        getAll()
    }

    //initialize activity
    private fun initActivity() {
        if (FavoriteFragment.activity == null)
            FavoriteFragment.activity = activity
    }

    //initialize visibility of Views after Fragment Created
    private fun initVisibility() {
    }

    private fun setup() {
        MainActivity.binding!!.bottomAppBar.menu.findItem(R.id.favorite).isChecked = true
    }

    private fun getAll() {
        Observable.fromCallable {
            var db = AppDatabase.getDatabase(context = activity)
            var favoriteDao = db?.favoriteDao()

            with(favoriteDao) {
                list = this!!.getAll()
            }
        }.doOnComplete { setFavorite() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun setFavorite() {
        binding?.leagues?.layoutManager = GridLayoutManager(activity, 2)
        binding?.leagues?.adapter =
            LeaguesAdapter(
                requireActivity(),
                list,
                object : LeaguesAdapter.OnItemClickListener {
                    override fun leagueClick(position: Int) {
                        Navigator.loadFragment(
                            activity,
                            LeagueDetailsFragment.newInstance(activity),
                            R.id.fragment_container,
                            true
                        )
                    }

                    override fun favoriteClick(position: Int,holder: LeaguesAdapter.MyViewHolder) {
                    }
                }
            )
    }

}