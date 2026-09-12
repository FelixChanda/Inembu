/*
 * Copyright (C) 2026 xLexip <https://lexip.dev>
 *
 * Licensed under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0
 *
 * Please see the License for specific terms regarding permissions and limitations.
 */

package dev.lexip.hecate.ads

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

private const val TAG = "AdBanner"

@Composable
fun AdBanner(modifier: Modifier = Modifier, adWidthDp: Int? = null) {
	val context = LocalContext.current
	val configuration = LocalConfiguration.current
	val adWidth = adWidthDp ?: (configuration.screenWidthDp - 40)

	AndroidView(
		modifier = modifier.fillMaxWidth(),
		factory = { ctx ->
			AdView(ctx).apply {
				adUnitId = AdMobGate.BANNER_AD_UNIT_ID
				adSize = AdSize.getLargeAnchoredAdaptiveBannerAdSize(ctx, adWidth)
				loadAd(AdRequest.Builder().build())
				Log.d(TAG, "Loaded banner ad for unit $adUnitId with size $adSize")
			}
		},
		update = { adView ->
			adView.adSize = AdSize.getLargeAnchoredAdaptiveBannerAdSize(context, adWidth)
		},
		onRelease = { adView ->
			adView.destroy()
		}
	)
}