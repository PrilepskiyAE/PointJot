package com.prilepskiy.pointjot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.prilepskiy.pointjot.ui.screen.RootScreen
import com.prilepskiy.pointjot.ui.theme.PointJotTheme
import com.yandex.mobile.ads.appopenad.AppOpenAd
import com.yandex.mobile.ads.appopenad.AppOpenAdEventListener
import com.yandex.mobile.ads.appopenad.AppOpenAdLoadListener
import com.yandex.mobile.ads.appopenad.AppOpenAdLoader
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError

import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import dagger.hilt.android.AndroidEntryPoint
private const val AD_UNIT_ID = "demo-appopenad-yandex"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var appOpenAd: AppOpenAd?=null
    private var isAdShownOnColdStart=false
    private inner class AdEventListener : AppOpenAdEventListener {
        override fun onAdShown() {
            // Called when ad is shown.
        }

        override fun onAdFailedToShow(adError: AdError) {
            // Called when ad failed to show.
            clearAppOpenAd()
            loadAppOpenAd()
        }

        override fun onAdDismissed() {
            // Called when ad is dismissed.
            // Clean resources after dismiss and preload new ad.
            clearAppOpenAd()
            loadAppOpenAd()
        }


        override fun onAdClicked() {
            // Called when a click is recorded for an ad.
        }

        override fun onAdImpression(impressionData: ImpressionData?) {
            // Called when an impression is recorded for an ad.
            // Get Impression Level Revenue Data in argument.
        }
    }
    private fun loadAppOpenAd() {
        val appOpenAdLoader: AppOpenAdLoader = AppOpenAdLoader(application)

        val adRequestConfiguration = AdRequestConfiguration.Builder(AD_UNIT_ID).build()
        val appOpenAdLoadListener = object : AppOpenAdLoadListener {
            override fun onAdLoaded(appOpenAd: AppOpenAd) {
                this@MainActivity.appOpenAd = appOpenAd
                if (!isAdShownOnColdStart){
                    showAppOpenAd()
                    isAdShownOnColdStart=true
                }
            }

            override fun onAdFailedToLoad(error: AdRequestError) {}
        }
        appOpenAdLoader.setAdLoadListener(appOpenAdLoadListener)
        appOpenAdLoader.loadAd(adRequestConfiguration)
    }

    private fun clearAppOpenAd() {
        appOpenAd?.setAdEventListener(null)
        appOpenAd=null
    }
    private fun showAppOpenAd(){
    val appOpenAdEventListener = AdEventListener()
        appOpenAd?.setAdEventListener(appOpenAdEventListener)
        appOpenAd?.show(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PointJotTheme {
                SetNavigationBarColor()
                RootScreen()
            }
        }
        MobileAds.initialize(this) {
            loadAppOpenAd()
//            val processLifecycleObserver = DefaultProcessLifecycleObserver(
//                onProcessCameForeground = ::showAppOpenAd
//            )
//            ProcessLifecycleOwner.get().lifecycle.addObserver(processLifecycleObserver)

        }
    }
}

