package com.football.league.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.football.league.Model.responses.GetLeagues
import com.football.league.repository.LeaguesRepository

class LeaguesViewModel (application: Application) : AndroidViewModel(application) {
    var repository: LeaguesRepository
    init {
        repository = LeaguesRepository(application)
    }
    fun leagues(): LiveData<GetLeagues?> {
        return repository.leaguesApi()
    }
}