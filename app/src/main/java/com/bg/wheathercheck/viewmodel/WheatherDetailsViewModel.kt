package com.bg.wheathercheck.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bg.wheathercheck.model.CityWeatherObj
import com.bg.wheathercheck.model.FiveDaysForeCast
import com.bg.wheathercheck.network.NetworkConnectionInterceptor
import com.bg.wheathercheck.utils.lazyDeferred
import com.bg.wheathercheck.viewmodel.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WheatherDetailsViewModel(val
    repository: WeatherRepository,
 val  networkConnectionInterceptor: NetworkConnectionInterceptor
) : ViewModel() {

    val cityWeatherData by lazyDeferred {
       // repository.fetchCityWeather()
    }

    val cityWeatherNextFiveDayData by lazyDeferred {
        //repository.fetchNextFiveDaysWeather()
    }





    suspend fun getFiveDayInfo(latStr: String, long: String): MutableLiveData<FiveDaysForeCast> {
        return withContext(Dispatchers.IO) {
            repository.fetchNextFiveDaysWeather(latStr,long)
        }
    }

    suspend fun getCityInfo(latStr: String, long: String): MutableLiveData<CityWeatherObj> {

        return withContext(Dispatchers.IO) {
            repository.fetchCityWeather(latStr,long)
        }
    }

}