package com.football.league.classes

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.provider.Settings
import java.util.*

class AppController : Application() {
    var mInstance: AppController? = null
    var locale: Locale? = null
    var lang: String? = null

    fun getContext(): Context? {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        val config: Configuration = getBaseContext().getResources().getConfiguration()
        GlobalFunctions.setUpFont()
    }

    @Synchronized
    fun getInstance(): AppController? {
        if (mInstance == null) {
            try {
                mInstance = AppController::class.java.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return mInstance
    }

}


