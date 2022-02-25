package com.football.league.database.room.DAOs

import androidx.room.*
import com.football.league.Model.responses.Competition
import com.football.league.database.room.entities.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Query("SELECT competition FROM favoriteLeagues")
    fun getAll() : List<Competition>
}