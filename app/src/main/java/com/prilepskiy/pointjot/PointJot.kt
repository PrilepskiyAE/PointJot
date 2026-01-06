package com.prilepskiy.pointjot

import android.app.Application
import com.yandex.mobile.ads.appopenad.AppOpenAd
import com.yandex.mobile.ads.appopenad.AppOpenAdLoadListener
import com.yandex.mobile.ads.appopenad.AppOpenAdLoader
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PointJot : Application(){
    override fun onCreate() {
        super.onCreate()

    }
}