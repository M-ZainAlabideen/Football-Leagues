package com.football.league.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.football.league.Model.responses.Competition

@Entity(tableName = "favoriteLeagues")
data class Favorite(
    @PrimaryKey
    val id: Int?,
    val competition: Competition?
)
