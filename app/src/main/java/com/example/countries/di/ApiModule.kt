package com.example.countries.di

import com.example.countries.model.CountriesApi
import com.example.countries.model.CountriesService
import com.example.countries.ui.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://raw.githubusercontent.com"

val apiModule = module {
    single { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java) as CountriesApi }

    single { CountriesService(get()) }

    viewModel { ListViewModel(get()) }
}