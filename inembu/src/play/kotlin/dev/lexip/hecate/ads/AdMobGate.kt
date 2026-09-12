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

import android.content.Context
import com.google.android.gms.ads.MobileAds

object AdMobGate {

	const val BANNER_AD_UNIT_ID = "ca-app-pub-6112734772406406/3052098308"

	@Volatile
	private var initialized = false

	fun init(context: Context) {
		if (initialized) return
		/**
		 * Load ads as early as possible so users see content sooner.
		 * The SDK is initialized on the main thread and only blocks the first ad load,
		 * not app startup.
		 */
		MobileAds.initialize(context) {}
		initialized = true
	}
}