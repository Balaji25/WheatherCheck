package com.bg.wheathercheck.model

data class ListItem(val dt: Int = 0,
                    val pop: Double = 0.0,
                    val rain: Rain,
                    val visibility: Int = 0,
                    val dt_txt: String = "",
                    val weather: List<WeatherItem>?,
                    val main: Main,
                    val clouds: Clouds,
                    val sys: Sys,
                    val wind: Wind)