package com.bg.wheathercheck.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bg.callhistory.callback.OnClickCity
import com.bg.wheathercheck.R
import com.bg.wheathercheck.databinding.WeatherListItemBinding
import com.bg.wheathercheck.model.FiveDaysForeCast
import com.bg.wheathercheck.utils.AppUtils
import com.bg.wheathercheck.view.ui.wheatherdetails.WeatherDetailsFragment

/**
 * Created by Balaji Gaikwad on 23/12/20.
 */
class FiveDayWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var colors = intArrayOf(
        R.color.color_plate_1,
        R.color.color_plate_2,
        R.color.color_plate_3,
        R.color.color_plate_4,
        R.color.color_plate_5,

        )
    private  var fiveDayWeatherListModel: FiveDaysForeCast?=null
    val appUtils = AppUtils
    private  lateinit  var mContext: Context

    // private var arrayList: ArrayList<ListItem> = ArrayList<ListItem>()
    private var callback: OnClickCity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /* val mDeveloperListItemBinding = DataBindingUtil.inflate<EventListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_list_item, parent, false
        )*/


        val mWeatherListItemBinding = DataBindingUtil.inflate<WeatherListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_list_item, parent, false
        )

        return FiveDayWeatherViewHolder(mWeatherListItemBinding)
    }


    override fun onBindViewHolder(rosterViewHolder: RecyclerView.ViewHolder, position: Int) {

        val listItem = fiveDayWeatherListModel?.list?.get(position)

        val headerViewHolder = rosterViewHolder as FiveDayWeatherViewHolder

        rosterViewHolder.weatherListItemBinding.cityWeatherObj = listItem
        rosterViewHolder.weatherListItemBinding.appUtils = appUtils

        val colorId = (Math.random() * colors.size).toInt()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            rosterViewHolder.weatherListItemBinding.cardView.setCardBackgroundColor(mContext.getColor(colors[colorId])/* mContext.getColor(R.color.color_plate_1)*/)
        }

        rosterViewHolder.weatherListItemBinding.rootLayout.setOnClickListener {


        }


    }


    override fun getItemCount(): Int {
        return if (fiveDayWeatherListModel != null) {
            fiveDayWeatherListModel!!.list.size
        } else {
            0
        }
    }


    fun setInboxList(
        context1: WeatherDetailsFragment,
        context: Context,
        eventListModel: FiveDaysForeCast
    ) {
        this.fiveDayWeatherListModel = eventListModel
        this.mContext = context

        notifyDataSetChanged()
    }


    class FiveDayWeatherViewHolder(var weatherListItemBinding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(weatherListItemBinding.root)


}

