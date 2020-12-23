package com.bg.wheathercheck.network


import com.bg.wheathercheck.model.CityWeatherObj
import com.bg.wheathercheck.model.FiveDaysForeCast
import com.bg.wheathercheck.utils.AppConstant
import com.bg.wheathercheck.utils.AppConstant.FORECAST_INFO
import com.bg.wheathercheck.utils.AppConstant.WEATHER_INFO


import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("$WEATHER_INFO")
    suspend fun getCityWeatherData(@Query("lat") lat: String?, @Query("lon") lon: String?,
                                   @Query("appid") appiId: String?): Response<CityWeatherObj>

    @GET("$FORECAST_INFO")
    suspend  fun getNextFiveDaysCityWeatherData(@Query("lat") lat: String?,@Query("lon") lon: String?,
                                                @Query("appid") appiId: String?,@Query("units") units: String?): Response<FiveDaysForeCast>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiEndPoint {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(AppConstant.CONST_APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build()
                .create(ApiEndPoint::class.java)
        }
    }

}

