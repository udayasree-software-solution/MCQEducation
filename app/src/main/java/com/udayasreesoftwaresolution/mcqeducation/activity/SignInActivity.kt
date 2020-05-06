package com.udayasreesoftwaresolution.mcqeducation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.udayasreesoftwaresolution.mcqeducation.R
import com.udayasreesoftwaresolution.mcqeducation.fragment.LoginFragment
import com.udayasreesoftwaresolution.mcqeducation.fragment.RegisterFragment
import com.udayasreesoftwaresolution.mcqeducation.progressutils.ProgressStyle
import com.udayasreesoftwaresolution.mcqeducation.progressutils.USProgressBar

class SignInActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentListener,
    RegisterFragment.OnRegisterFragmentListener {

    private var mFragmentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initView()
    }

    private fun initView() {
        mFragmentPosition = 0
        launchFragment(LoginFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment?) {
        if (fragment != null) {
            clearBackStack()
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.fragment_slide_left_enter,
                    R.anim.fragment_slide_left_exit,
                    R.anim.fragment_slide_right_enter,
                    R.anim.fragment_slide_right_exit
                )
                .replace(R.id.signin_fragment_container, fragment)
                .addToBackStack(fragment::class.java.simpleName)
                .commit()
        }
    }

    private fun clearBackStack() {
        try {
            val fragments: Int = supportFragmentManager.backStackEntryCount
            for (i in fragments downTo 1) {
                supportFragmentManager.popBackStackImmediate()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onFragmentContextFailListener() {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setMessage("Something went wrong. Please restart your application")
            .setPositiveButton(
                "Exit"
            ) { dialog, _ ->
                clearBackStack()
                dialog?.dismiss()
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                clearBackStack()
                startActivity(intent)
                finishAffinity()
            }
            .create()
            .show()
    }

    override fun onLoginFragmentListener() {
        mFragmentPosition = 0
        launchFragment(LoginFragment.newInstance())
    }

    override fun onSuccessfulRegisterListener() {
        Toast.makeText(this, "Successfully registered with us!", Toast.LENGTH_SHORT).show()
        mFragmentPosition = 0
        launchFragment(LoginFragment.newInstance())
    }

    override fun onRegisterFragmentListener() {
        mFragmentPosition = 1
        launchFragment(RegisterFragment.newInstance())
    }

    override fun onLoginCredentialListener() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        if (mFragmentPosition == 1) {
            mFragmentPosition = 0
            launchFragment(LoginFragment.newInstance())
        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            clearBackStack()
            startActivity(intent)
            finishAffinity()
            super.onBackPressed()
        }
    }
}