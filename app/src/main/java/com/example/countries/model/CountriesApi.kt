package com.example.countries.model

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): List<Country>
}