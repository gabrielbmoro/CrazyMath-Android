package com.gabrielbmoro.crazymath.helpers

import android.content.Context
import com.gabrielbmoro.crazymath.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class AdsHandler {

    private var counterToShowAds = CYCLE_SIZE_BETWEEN_ADS

    private fun isToShow() = counterToShowAds == 0

    fun showIfIsReady(context: Context) {
        counterToShowAds--

        if (isToShow()) {
            MobileAds.initialize(context)

            InterstitialAd(context).apply {
                adUnitId = context.getString(R.string.google_ads_unit_id)
                loadAd(AdRequest.Builder().build())
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        show()
                    }
                }
            }

            counterToShowAds = CYCLE_SIZE_BETWEEN_ADS
        }
    }

    companion object {
        private const val CYCLE_SIZE_BETWEEN_ADS = 3
    }
}