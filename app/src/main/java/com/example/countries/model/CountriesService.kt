package com.example.countries.model

import com.example.countries.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    suspend fun getCountries(): List<Country>? {
        return try {
            api.getCountries()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}