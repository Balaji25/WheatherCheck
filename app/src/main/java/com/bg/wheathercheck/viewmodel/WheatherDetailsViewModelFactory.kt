package com.bg.wheathercheck.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bg.wheathercheck.network.NetworkConnectionInterceptor
import com.bg.wheathercheck.viewmodel.repository.WeatherRepository

/**
 * Created by Balaji Gaikwad on 23/12/20.
 */
class WeatherDetailsViewModelFactory (private val repository: WeatherRepository,val networkConnectionInterceptor: NetworkConnectionInterceptor) : ViewModelProvider.NewInstanceFactory() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WheatherDetailsViewModel(repository,networkConnectionInterceptor) as T
    }
}