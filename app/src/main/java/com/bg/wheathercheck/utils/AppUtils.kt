package com.bg.wheathercheck.utils

import com.bg.wheathercheck.R
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

fun getDayName(dateStr: String):String {

    val inFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US)
    val date = inFormat.parse(dateStr)
    val outFormat = SimpleDateFormat("EEEE", Locale.US)
    val day = outFormat.format(date!!)
    return day;
}



    fun getConvertedTemp(temp: Double):String{
        return temp.toString().trim()
    }


    fun  convertIntToString(intValue: Int):String {
        return intValue.toString().trim()
    }


     fun convertKelvinToCelsius(kelvin: Double): String {
        return (kelvin - 273.15).toFloat().toString().trim()
    }


}