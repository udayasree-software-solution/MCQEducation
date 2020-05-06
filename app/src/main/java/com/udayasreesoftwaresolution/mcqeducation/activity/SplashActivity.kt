package com.udayasreesoftwaresolution.mcqeducation.activity

import android.content.Intent
import android.graphics.Point
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.udayasreesoftwaresolution.mcqeducation.R
import com.udayasreesoftwaresolution.mcqeducation.retrofits.APICallRequest
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnDeviceVerifyRetroListener
import com.udayasreesoftwaresolution.mcqeducation.utils.AppUtils
import com.udayasreesoftwaresolution.mcqeducation.utils.FirebaseConstant
import com.udayasreesoftwaresolution.mcqeducation.utils.PreferenceUtils

class SplashActivity : AppCompatActivity(), OnDeviceVerifyRetroListener {

    private lateinit var splashImage : ImageView
    private lateinit var companyName : TextView
    private lateinit var preferenceUtils: PreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()
    }

    private fun initView() {
        screenSize()
        splashImage = findViewById(R.id.splash_image_id)
        companyName = findViewById(R.id.splash_company_name_id)
        companyName.typeface = AppUtils.getTypeFace(this, "")

        preferenceUtils =
            PreferenceUtils(this)
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        preferenceUtils.setAppVersion(pInfo.versionName)
        preferenceUtils.setSecurityKey("55646179615372656554656368")
        if (preferenceUtils.getOneTimeLogin()) {
            verifyUserDetails()
        } else {
            Handler().postDelayed({
                startActivity(Intent(this, SignInActivity::class.java))
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            },7000L)
        }

    }

    private fun verifyUserDetails() {
        if (AppUtils.networkConnectivityCheck(this)) {
            val apiCallRequest = APICallRequest(this)
            val deviceCodeListener = this as OnDeviceVerifyRetroListener
            apiCallRequest.verifyDeviceCodeFromServer(preferenceUtils.getUserEmail(), preferenceUtils.getCourseCode()
                , preferenceUtils.getStreamCode(), preferenceUtils.getClassCode(), preferenceUtils.getDeviceCode()
                ,deviceCodeListener)
        } else {
            finish()
        }
    }

    private fun screenSize() {
        val size = Point()
        val w = windowManager

        w.defaultDisplay.getSize(size)
        AppUtils.SCREEN_WIDTH = size.x
        AppUtils.SCREEN_HEIGHT = size.y
    }

    override fun onFailureDeviceCode(message: String) {
        if (message.isEmpty()) {
            Toast.makeText(this, R.string.server_error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    override fun onSuccessfulDeviceCode() {
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        },7000L)
    }
}
