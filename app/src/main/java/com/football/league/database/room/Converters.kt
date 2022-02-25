package com.football.league.database.room

import androidx.room.TypeConverter
import com.football.league.Model.responses.Competition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
        @TypeConverter
        fun fromCompetition(competition: Competition?): String? {
            if (competition == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<Competition>() {}.type
            return gson.toJson(competition, type)
        }

        @TypeConverter
        fun toCompetition(competition: String?): Competition? {
            if (competition == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<Competition>() {}.type
            return gson.fromJson<Competition>(competition, type)
    }
}