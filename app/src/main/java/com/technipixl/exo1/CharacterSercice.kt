package com.technipixl.exo1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterSercice {



    @Headers("Content-type: application/json")
    @GET("characters")
    suspend fun characters(@Query("apikey", encoded = true) publicKey: String, @Query("ts", encoded = true) ts : String, @Query(
        "hash", encoded = true) hash:String, @Query("limit", encoded = true) limit:String):Response<CharacterDataWrapper>

    @Headers("Content-type: application/json")
    @GET("characters/{id}")
    suspend fun comics(@Path("id", encoded = true) id :String,@Query("apikey", encoded = true) publicKey: String, @Query("ts", encoded = true) ts : String, @Query(
        "hash", encoded = true) hash: String):Response<CharacterDataWrapper>
}