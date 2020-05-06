package com.udayasreesoftwaresolution.mcqeducation.utils

import android.content.Context
import android.content.SharedPreferences


class PreferenceUtils(val context: Context) {
    private var sharedPreferences: SharedPreferences? = null
    private val one_time_login = "ONE_TIME_LOGIN"
    private val device_login_code = "DEVICE_LOGIN_CODE"

    private val user_name = "USER_NAME"
    private val user_mobile = "USER_MOBILE"
    private val user_email = "USER_EMAIL"

    private val course_code = "COURSE_CODE"
    private val stream_code = "STREAM_CODE"
    private val class_code = "CLASS_CODE"

    private val is_teacher = "IS_TEACHER"
    private val is_account_verified = "ACCOUNT_VERIFIED"

    private val app_version = "APP_VERSION"
    private val security_key = "SECURITY_KEY"

    init {
        sharedPreferences = context.getSharedPreferences("MCQ_EDUCATION", Context.MODE_PRIVATE)
    }

    fun setOneTimeLogin(loginStatus: Boolean) {
        sharedPreferences?.edit()?.putBoolean(one_time_login, loginStatus)?.apply()
    }

    fun getOneTimeLogin(): Boolean {
        return sharedPreferences?.getBoolean(one_time_login, false)!!
    }

    fun setDeviceCode(deviceCode: String) {
        sharedPreferences?.edit()?.putString(device_login_code, deviceCode)?.apply()
    }

    fun getDeviceCode(): String {
        return sharedPreferences?.getString(device_login_code, "")!!
    }


    /*----------------------------------------------------------------------------------------------------*/
    fun setUserName(userName: String) {
        sharedPreferences?.edit()?.putString(user_name, userName)?.apply()
    }

    fun getUserName(): String {
        return sharedPreferences?.getString(user_name, "")!!
    }


    fun setUserMobile(userMobile: String) {
        sharedPreferences?.edit()?.putString(user_mobile, userMobile)?.apply()
    }

    fun getUserMobile(): String {
        return sharedPreferences?.getString(user_mobile, "")!!
    }

    fun setUserEmail(userEmail: String) {
        sharedPreferences?.edit()?.putString(user_email, userEmail)?.apply()
    }

    fun getUserEmail(): String {
        return sharedPreferences?.getString(user_email, "")!!
    }

/*------------------------------------------------------------------------------------------------------------------*/

    fun setCourseCode(courseCode: String) {
        sharedPreferences?.edit()?.putString(course_code, courseCode)?.apply()
    }

    fun getCourseCode(): String {
        return sharedPreferences?.getString(course_code, "")!!
    }

    fun setStreamCode(streamCode: String) {
        sharedPreferences?.edit()?.putString(stream_code, streamCode)?.apply()
    }

    fun getStreamCode(): String {
        return sharedPreferences?.getString(stream_code, "")!!
    }

    fun setClassCode(classCode: String) {
        sharedPreferences?.edit()?.putString(class_code, classCode)?.apply()
    }

    fun getClassCode(): String {
        return sharedPreferences?.getString(class_code, "")!!
    }

/*------------------------------------------------------------------------------------------------------------------*/

    fun setIsTeacher(isTeacher : Boolean) {
        sharedPreferences?.edit()?.putBoolean(is_teacher, isTeacher)?.apply()
    }

    fun getISTeacher(): Boolean {
        return sharedPreferences?.getBoolean(is_teacher, false)!!
    }

    fun setIsAccountVerify(isAccountVerify: Boolean) {
        sharedPreferences?.edit()?.putBoolean(is_account_verified, isAccountVerify)?.apply()
    }

    fun getIsAccountVerify(): Boolean {
        return sharedPreferences?.getBoolean(is_account_verified, false)!!
    }

/*------------------------------------------------------------------------------------------------------------------*/
    fun setAppVersion(appVersion: String) {
        sharedPreferences?.edit()?.putString(app_version, appVersion)?.apply()
    }

    fun getAppVersion(): String {
        return sharedPreferences?.getString(app_version, "")!!
    }

    fun setSecurityKey(key: String) {
        sharedPreferences?.edit()?.putString(security_key, key)?.apply()
    }

    fun getSecurityKey(): String {
        return sharedPreferences?.getString(security_key, "")!!
    }
/*------------------------------------------------------------------------------------------------------------------*/

    fun clearPreference() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}