package com.football.league.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.football.league.Model.responses.GetLeagues
import com.football.league.network.Retrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class LeaguesRepository(application: Application) {
    private var application: Application? = null
    private var mutableLiveData: MutableLiveData<GetLeagues>? = null

    init {
        this.application = application
    }

    fun leaguesApi(): LiveData<GetLeagues?> {
        mutableLiveData = MutableLiveData<GetLeagues>()
        val leagues: Observable<GetLeagues?> =
            Retrofit.getRetrofitServices(application)!!.leagues()
        leagues.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer { getLeagues: GetLeagues? -> handleResults(getLeagues!!) },
                Consumer { t: Throwable -> handleError(t) }
            )
        return mutableLiveData as MutableLiveData<GetLeagues>
    }


    private fun handleResults(getLeagues: GetLeagues) {
        mutableLiveData!!.value = getLeagues
    }

    private fun handleError(t: Throwable) {
        //handle error
    }

}