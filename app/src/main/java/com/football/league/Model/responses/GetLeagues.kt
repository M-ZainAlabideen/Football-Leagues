package com.football.league.Model.responses

import com.google.gson.annotations.SerializedName

data class GetLeagues(

    val competitions: List<Competition>?,
    val count: Int?,
    val filters: Filters?
)

data class Competition(
    val name: String?,
    @SerializedName("code")
    val shortName: String?,
    val emblemUrl: String?,
    val id: Int?,
    val lastUpdated: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?
)

class Filters
