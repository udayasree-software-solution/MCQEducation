package com.udayasreesoftwaresolution.mcqeducation.retrofits

class URLUtils {
    companion object {
        private const val URL_LIVE = ""
        private const val URL_TEST = "http://192.168.225.32/"
        private const val is_debug = true
        @JvmField
        val BASE_URL = if(is_debug){
            URL_TEST
        }else{
            URL_LIVE
        }
    }
}