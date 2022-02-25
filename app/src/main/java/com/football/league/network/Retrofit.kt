package com.football.league.network

import android.content.Context
import com.football.league.classes.Constants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Retrofit {
    companion object{
        private var apiInterface: ApiInterface? = null
        private val logging = HttpLoggingInterceptor()
        private var retrofit: Retrofit? = null

        fun getRetrofitServices(context: Context?): ApiInterface? {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            })
            httpClient.addInterceptor(logging)
            val gson = GsonBuilder()
                .setLenient()
                .create()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java).also { apiInterface = it }
        }

    }


}