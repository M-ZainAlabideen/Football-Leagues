package com.football.league.room.DAOs

import androidx.room.*
import com.football.league.Model.responses.Competition
import com.football.league.room.entities.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM favoriteLeagues")
    fun getAll() : List<Competition>
}