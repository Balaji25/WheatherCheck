package com.bg.wheathercheck.viewmodel.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.bg.wheathercheck.model.CityWeatherObj
import com.bg.wheathercheck.model.FiveDaysForeCast
import com.bg.wheathercheck.network.AbstractTaskApiRequest
import com.bg.wheathercheck.network.ApiEndPoint
import com.bg.wheathercheck.network.NetworkConnectionInterceptor
import com.bg.wheathercheck.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Balaji Gaikwad on 23/12/20.
 */
class WeatherRepository (val apiEndPoint: ApiEndPoint, val networkConnectionInterceptor: NetworkConnectionInterceptor) : AbstractTaskApiRequest(){



    private val cityWeatherMLiveData = MutableLiveData<CityWeatherObj>()

    private val cityNextFiveDaysForeCastMLiveData = MutableLiveData<FiveDaysForeCast>()


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    suspend fun fetchCityWeather(latStr: String, long: String): MutableLiveData<CityWeatherObj> {
        return withContext(Dispatchers.IO) {
            var   response = apiRequest { apiEndPoint.getCityWeatherData(latStr,long,AppConstant.API_KEY) }
            cityWeatherMLiveData.postValue(response)
           // saveEvents(response)
            cityWeatherMLiveData
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    suspend fun fetchNextFiveDaysWeather(latStr: String, long: String): MutableLiveData<FiveDaysForeCast> {
        return withContext(Dispatchers.IO) {
            var   response = apiRequest { apiEndPoint.getNextFiveDaysCityWeatherData(latStr,long,AppConstant.API_KEY,AppConstant.UNITS) }
            cityNextFiveDaysForeCastMLiveData.postValue(response)
            cityNextFiveDaysForeCastMLiveData
        }
    }

}