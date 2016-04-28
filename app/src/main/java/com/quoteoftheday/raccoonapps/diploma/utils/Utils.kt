package com.quoteoftheday.raccoonapps.diploma.utils

import android.util.Log


fun <T> T.toLog(tag: String) {
    Log.d(tag, this.toString())
}