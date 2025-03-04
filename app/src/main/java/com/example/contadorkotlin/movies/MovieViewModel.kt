package com.example.contadorkotlin.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel : ViewModel() {

     val movies = MutableLiveData<List<Movie>>()
     val error = MutableLiveData<String>()

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val call = getRetrofit().create(MovieApiService::class.java).getTopRatedMovies()
            val moviesResponse = call.body()

            launch(Dispatchers.Main) {
                if (call.isSuccessful) {
                    movies.value = moviesResponse?.results ?: emptyList()

                } else {
                    error.value = "Error al cargar películas"
                }
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
