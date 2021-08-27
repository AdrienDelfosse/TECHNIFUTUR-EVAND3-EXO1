package com.technipixl.exo1

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CharacterServiceImpl {

    companion object {
        const val baseUrl = "https://gateway.marvel.com:443/v1/public/"
    }

    fun isNetworkAvailable(context: Context) = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        getNetworkCapabilities(activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }

    private fun getRetrofit(): Retrofit {
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }

    suspend fun getCharacters(publicKey: String, ts: String, hash:String, limit:String): Response<CharacterDataWrapper> = getRetrofit().create(CharacterSercice::class.java).characters(publicKey, ts, hash, limit)
    suspend fun getComics(id:String, publicKey: String, ts: String, hash:String): Response<CharacterDataWrapper> = getRetrofit().create(CharacterSercice::class.java).comics(id, publicKey, ts, hash)
}