package com.example.countries.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.di.DaggerApiComponent
import com.example.countries.model.CountriesService
import com.example.countries.model.Country
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var countriesService: CountriesService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        viewModelScope.launch {
            val data = countriesService.getCountries()

            data?.let {
                countries.postValue(data)
                countryLoadError.postValue(false)
                loading.postValue(false)
            } ?: run {
                countryLoadError.postValue(true)
                loading.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}