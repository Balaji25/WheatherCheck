package com.bg.wheathercheck.view.ui.wheatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bg.wheathercheck.R
import com.bg.wheathercheck.adapter.FiveDayWeatherAdapter
import com.bg.wheathercheck.databinding.WheatherDetailsFragmentBinding
import com.bg.wheathercheck.model.CityWeatherObj
import com.bg.wheathercheck.model.FiveDaysForeCast
import com.bg.wheathercheck.utils.ApiException
import com.bg.wheathercheck.utils.AppUtils
import com.bg.wheathercheck.utils.Coroutines
import com.bg.wheathercheck.utils.NoInternetException
import com.bg.wheathercheck.view.ui.wheatherdetails.MapsFragment.Companion.EXTRA_LAT
import com.bg.wheathercheck.view.ui.wheatherdetails.MapsFragment.Companion.EXTRA_LONG
import com.bg.wheathercheck.viewmodel.WeatherDetailsViewModelFactory
import com.bg.wheathercheck.viewmodel.WheatherDetailsViewModel
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.io.Serializable
import java.util.ArrayList

class WeatherDetailsFragment : Fragment(), KodeinAware {


    private lateinit var fiveDayWeatherAdapter: FiveDayWeatherAdapter
    private lateinit var longitudeStr: String
    private lateinit var latitudeStr: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var wheatherDetailsFragmentBinding: WheatherDetailsFragmentBinding
    private lateinit var weatherDetailsViewModel: WheatherDetailsViewModel
    private val factory: WeatherDetailsViewModelFactory by instance()
    override val kodein by kodein()


    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }

    private lateinit var viewModel: WheatherDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        weatherDetailsViewModel = ViewModelProvider(this, factory).get(WheatherDetailsViewModel::class.java)


         wheatherDetailsFragmentBinding = DataBindingUtil.inflate(
             inflater,
             R.layout.wheather_details_fragment,
             container,
             false
         )
        wheatherDetailsFragmentBinding.utils=AppUtils
        initUIAndData()
        return wheatherDetailsFragmentBinding.root
    }




    fun initUIAndData(){

        // bind nestedScrollView
        nestedScrollView = wheatherDetailsFragmentBinding?.nestedScrollView

        swipeContainer = wheatherDetailsFragmentBinding?.swipeContainer

        recyclerView = wheatherDetailsFragmentBinding?.recyclerView

        recyclerView!!.setLayoutManager(LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false))
        recyclerView!!.setHasFixedSize(true)

        //init the Custom adataper
        fiveDayWeatherAdapter = FiveDayWeatherAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(fiveDayWeatherAdapter)


      swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        swipeContainer.setOnRefreshListener(OnRefreshListener {

            bindCityWeatherUI(latitudeStr,longitudeStr)
            bindCityNextFiveDayWeatherUI(latitudeStr,longitudeStr)
            //swipeContainer.setRefreshing(false)
        })



    }


    private fun bindCityWeatherUI(latStr:String,long:String) = Coroutines.main {

        try {
            lifecycleScope.launch {
                weatherDetailsViewModel.getCityInfo(latStr,long).observe(viewLifecycleOwner,{
                    updateCityData(it)
                })
            }

        } catch (e: ApiException) {
            e.printStackTrace()
        } catch (e: NoInternetException) {
            e.printStackTrace()
        }



    }

    private fun bindCityNextFiveDayWeatherUI(latStr:String,long:String) = Coroutines.main {
        try {
            lifecycleScope.launch {
                weatherDetailsViewModel.getFiveDayInfo(latStr,long).observe(viewLifecycleOwner,{
                    updateCityNextFiveDayWeatherData(it)
                })
            }

        } catch (e: ApiException) {
            e.printStackTrace()
        } catch (e: NoInternetException) {
            e.printStackTrace()
        }



    }

    private fun updateCityNextFiveDayWeatherData(fiveDaysForeCast: FiveDaysForeCast?) {

        if (fiveDaysForeCast != null) {
            fiveDayWeatherAdapter?.setInboxList(this,requireActivity(),  fiveDaysForeCast)
        }


    }


    private fun updateCityData(cityWeatherObj: CityWeatherObj?) {
        wheatherDetailsFragmentBinding.cityWeatherObj=cityWeatherObj

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (arguments!=null && arguments?.getString(EXTRA_LAT)!=null && arguments?.getString(EXTRA_LONG)!=null){
            // Log.d("data", (args.toString()))
            latitudeStr= arguments?.getString(EXTRA_LAT,)!!
            longitudeStr= arguments?.getString(EXTRA_LONG)!!


            bindCityWeatherUI(latitudeStr,longitudeStr)
            bindCityNextFiveDayWeatherUI(latitudeStr,longitudeStr)
        }

    }

}