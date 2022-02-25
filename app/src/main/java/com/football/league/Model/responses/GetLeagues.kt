package com.football.league.Model.responses

data class GetLeagues(

    val competitions: List<Competition>?,
    val count: Int?,
    val filters: Filters?
)

data class Competition(
    val code: String?,
    val emblemUrl: String?,
    val id: Int?,
    val lastUpdated: String?,
    val name: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?
)

class Filters
