package com.example.contadorkotlin.movies

import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {
    @GET("movie/top_rated?api_key=53d2be4bef46f19a4127a8249e71de9e&language=es-ES&page=1")
    suspend fun getTopRatedMovies(): Response<MovieResponse>
}
