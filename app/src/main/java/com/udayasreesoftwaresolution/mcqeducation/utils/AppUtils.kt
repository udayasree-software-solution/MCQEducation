package com.udayasreesoftwaresolution.mcqeducation.utils

import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AppUtils {
    companion object {
        @JvmField
        var ProgressStyle = com.udayasreesoftwaresolution.mcqeducation.progressutils.ProgressStyle.WAVE

        @JvmStatic
        var SCREEN_WIDTH = 0
        @JvmStatic
        var SCREEN_HEIGHT = 0

        @JvmStatic
        fun generateDeviceCode() : String {
            var code = ""
            val generator = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789"
            for (i in 0 until 16) {
                code += generator[Random().nextInt(generator.length)]
            }
            return code
        }

        @JvmStatic
        fun getTypeFace(context : Context, title : String) : Typeface {
            return when(title) {
                "title" -> {
                    Typeface.createFromAsset(context.assets, "fonts/DMSerifText-Regular.ttf")
                }
                else -> {
                    Typeface.createFromAsset(context.assets, "fonts/Scheherazade-Regular.ttf")
                }
            }
        }

        @JvmStatic
        fun networkConnectivityCheck(context: Context?): Boolean {
            val connectivityManager: ConnectivityManager? =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                if (networkInfo != null && networkInfo.isConnected) {
                    return true
                }
            }
            Toast.makeText(context, "Required Internet Connection", Toast.LENGTH_LONG).show()
            return false
        }
    }
}