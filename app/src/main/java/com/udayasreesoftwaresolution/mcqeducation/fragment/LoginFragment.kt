package com.udayasreesoftwaresolution.mcqeducation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.udayasreesoftwaresolution.mcqeducation.R
import com.udayasreesoftwaresolution.mcqeducation.progressutils.ProgressStyle
import com.udayasreesoftwaresolution.mcqeducation.progressutils.USProgressBar
import com.udayasreesoftwaresolution.mcqeducation.retrofits.APICallRequest
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnUserLoginRetroListener
import com.udayasreesoftwaresolution.mcqeducation.utils.AppUtils
import com.udayasreesoftwaresolution.mcqeducation.utils.PreferenceUtils
import java.util.regex.Pattern

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment(), View.OnClickListener, OnUserLoginRetroListener {

    private lateinit var loginTitle: TextView
    private lateinit var resetPasswordText: TextView
    private lateinit var registerText: TextView
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailInputlayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginButton: Button

    private lateinit var preferenceUtils: PreferenceUtils
    private lateinit var usProgress: USProgressBar

    private var mContext: Context? = null
    private lateinit var listener: OnLoginFragmentListener

    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mContext = context
            listener = context as OnLoginFragmentListener
        } catch (e: Exception) {
            throw RuntimeException("$context must implement LoginFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        if (mContext != null) {
            initView(view)
        } else {
            listener.onFragmentContextFailListener()
        }
        return view
    }

    private fun initView(view: View) {
        try {
            loginTitle = view.findViewById(R.id.login_title_id)
            resetPasswordText = view.findViewById(R.id.login_reset_password_id)
            registerText = view.findViewById(R.id.login_register_id)
            emailInputlayout = view.findViewById(R.id.login_email_layout_id)
            passwordInputLayout = view.findViewById(R.id.login_password_layout_id)
            emailEditText = view.findViewById(R.id.login_email_text_id)
            passwordEditText = view.findViewById(R.id.login_password_text_id)
            loginButton = view.findViewById(R.id.login_login_btn_id)

            loginButton.layoutParams.width = (AppUtils.SCREEN_WIDTH * 0.40).toInt()

            val fontGeneral = AppUtils.getTypeFace(mContext!!, "")
            loginTitle.typeface = AppUtils.getTypeFace(mContext!!, "title")
            resetPasswordText.typeface = fontGeneral
            registerText.typeface = fontGeneral
            emailEditText.typeface = fontGeneral
            passwordEditText.typeface = fontGeneral
            emailInputlayout.typeface = fontGeneral
            passwordInputLayout.typeface = fontGeneral
            loginButton.typeface = fontGeneral

            preferenceUtils = PreferenceUtils(mContext!!)

            resetPasswordText.setOnClickListener(this)
            registerText.setOnClickListener(this)
            loginButton.setOnClickListener(this)

            usProgress = USProgressBar(mContext!!)
            usProgress.setDescriptionText(R.string.connecting)
            usProgress.setTitleText(R.string.loading)
            usProgress.setProgressStyle(AppUtils.ProgressStyle)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_reset_password_id -> {
                /*TODO: Go to password reset fragment*/
            }

            R.id.login_register_id -> {
                listener.onRegisterFragmentListener()
            }

            R.id.login_login_btn_id -> {
                loginVerification()
            }
        }
    }

    private fun loginVerification() {
        if (AppUtils.networkConnectivityCheck(mContext)) {
            val emailIdET = emailEditText.text.toString().trim()
            val passwordET = passwordEditText.text.toString().trim()
            if (isValidEmailId(emailIdET) && passwordET.isNotEmpty()) {
                usProgress.showDialog()
                passwordEditText.setText("")

                val apiCallRequest = APICallRequest(mContext)
                val loginListener = this as OnUserLoginRetroListener
                apiCallRequest.getUserDetailFromServer(emailIdET, passwordET, loginListener)
            } else {
                if (!isValidEmailId(emailIdET)) {
                    emailEditText.error = mContext?.getString(R.string.email_error)
                }
                if (passwordET.isEmpty()) {
                    passwordEditText.error = mContext?.getString(R.string.password_error)
                }
            }
        }
    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    interface OnLoginFragmentListener {
        fun onFragmentContextFailListener()
        fun onRegisterFragmentListener()
        fun onLoginCredentialListener()
    }

    override fun onFailureLoginDetail(message: String) {
        usProgress.dismissDialog()
        if(message.isEmpty()){
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessfulLoginDetail() {
        usProgress.dismissDialog()
        listener.onLoginCredentialListener()
    }
}
