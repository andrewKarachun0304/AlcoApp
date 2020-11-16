package com.andrewkarachun0304.alcoapp.network

import com.andrewkarachun0304.alcoapp.dto.CocktailsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {
    @GET("api/json/v1/1/search.php")
    fun getCocktailsByNameAsync(@Query("s") name: String): Deferred<Response<CocktailsResponse>>
}