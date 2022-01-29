package com.rnsoft.newyorkapidemo

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun fetchNewYorkApiModel(
        @Path("section") section:String,
        @Path("period") period:Int,
        @Query("api-key") apikey: String
    ):NewYorkApiModel



    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=kjWL3IMsOTCUgfZWnsq9HJix6bGqMjGS")
    suspend fun fetchNewYorkApiModelTest():NewYorkApiModel

}