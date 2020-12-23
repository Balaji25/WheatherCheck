package com.bg.wheathercheck.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bg.wheathercheck.R
import com.google.android.material.card.MaterialCardView


@BindingAdapter("image")
fun loadLottieAnim(animationView: LottieAnimationView, weatherType: String?) {

    animationView.setRepeatCount(0)
    weatherType?.let {
        if (weatherType.contains(AppConstant.CONST_CLOUDS,true)){
            animationView.setAnimation("cloudy.json")
        }else if (weatherType.contains(AppConstant.CONST_RAIN,true)) {
            animationView.setAnimation("rainy.json")
        }else if (weatherType.contains(AppConstant.CONST_CLEAR,true)){
            animationView.setAnimation("clear_sky.json")
        }else if (weatherType.contains(AppConstant.CONST_SNOW,true)){
            animationView.setAnimation("sno_sunny.json")
        }else if (weatherType.contains(AppConstant.CONST_STORM,true)){
            animationView.setAnimation("storm.json")
        }else if (weatherType.contains(AppConstant.CONST_WIND,true)){
            animationView.setAnimation("windy.json")
        }
    }

    animationView.playAnimation()
    animationView.setRepeatCount(LottieDrawable.INFINITE)


}



