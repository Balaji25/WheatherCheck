package com.bg.wheathercheck.model

data class Main(val temp: Double = 0.0,
                val temp_min: Double = 0.0,
                val grnd_level: Int = 0,
                val tempKf: Double = 0.0,
                val humidity: Int = 0,
                val pressure: Int = 0,
                val seaLevel: Int = 0,
                val feelsLike: Double = 0.0,
                val temp_max: Double = 0.0)