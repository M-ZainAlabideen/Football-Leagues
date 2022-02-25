package com.football.league.network

import com.football.league.Model.responses.GetLeagues
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("competitions")
    fun leagues() : Observable<GetLeagues?>
}