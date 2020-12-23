package com.bg.wheathercheck

import android.app.Application
import com.bg.wheathercheck.network.ApiEndPoint
import com.bg.wheathercheck.network.NetworkConnectionInterceptor
import com.bg.wheathercheck.viewmodel.WeatherDetailsViewModelFactory
import com.bg.wheathercheck.viewmodel.repository.WeatherRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Balaji Gaikwad on 23/12/20.
 */
class WeatherDetailsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherDetailsApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiEndPoint(instance()) }
        bind() from singleton { WeatherRepository(instance(),instance() ) }

        bind() from provider { WeatherDetailsViewModelFactory(instance(),instance()) }

    }

}