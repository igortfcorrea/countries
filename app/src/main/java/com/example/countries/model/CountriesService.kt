package com.example.countries.model

import io.reactivex.Single

class CountriesService constructor(
    private val api: CountriesApi) {

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}