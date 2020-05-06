package com.udayasreesoftwaresolution.mcqeducation.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.udayasreesoftwaresolution.mcqeducation.R
import com.udayasreesoftwaresolution.mcqeducation.progressutils.USProgressBar
import com.udayasreesoftwaresolution.mcqeducation.retrofits.APICallRequest
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.*
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnCourseListRetroListener
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnSpecializationRetroList
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnUserRegisterRetroListener
import com.udayasreesoftwaresolution.mcqeducation.utils.AppUtils
import com.udayasreesoftwaresolution.mcqeducation.utils.PreferenceUtils
import org.json.JSONObject
import java.util.regex.Pattern

class RegisterFragment : Fragment(), View.OnClickListener, OnUserRegisterRetroListener,
    OnSpecializationRetroList, OnCourseListRetroListener {

    private lateinit var nameTextLayout: TextInputLayout
    private lateinit var mobileTextLayout: TextInputLayout
    private lateinit var emailTextLayout: TextInputLayout
    private lateinit var passwordTextLayout: TextInputLayout
    private lateinit var rePasswordTextLayout: TextInputLayout
    private lateinit var nameText: EditText
    private lateinit var mobileText: EditText
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var rePasswordText: EditText

    private lateinit var courseSpinner: Spinner
    private lateinit var streamSpinner: Spinner
    private lateinit var classSpinner: Spinner

    private lateinit var courseList: ArrayList<String>
    private lateinit var streamList: ArrayList<String>
    private lateinit var classList: ArrayList<String>

    private lateinit var courseModelList: ArrayList<Course>
    private lateinit var streamModelList: ArrayList<Specialization>

    private var courseSelectPos = 0
    private var streamSelectPos = 0
    private var classSelectPos = 0

    private lateinit var courseAdapter: ArrayAdapter<String>
    private lateinit var streamAdapter: ArrayAdapter<String>

    private lateinit var preferenceUtils: PreferenceUtils
    private lateinit var usProgress: USProgressBar

    private lateinit var listener: OnRegisterFragmentListener
    private var mContext: Context? = null

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mContext = context
            listener = context as OnRegisterFragmentListener
        } catch (e: Exception) {
            throw RuntimeException("$context must implement LoginFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        if (mContext != null) {
            initView(view)
        } else {
            listener.onFragmentContextFailListener()
        }
        return view
    }

    private fun initView(view: View) {
        val registerTitle: TextView = view.findViewById(R.id.register_title_id)
        val registerButton: Button = view.findViewById(R.id.register_register_btn_id)
        val loginText: TextView = view.findViewById(R.id.login_register_id)

        nameTextLayout = view.findViewById(R.id.register_name_layout_id)
        mobileTextLayout = view.findViewById(R.id.register_mobile_layout_id)
        emailTextLayout = view.findViewById(R.id.register_email_layout_id)
        passwordTextLayout = view.findViewById(R.id.register_password_layout_id)
        rePasswordTextLayout = view.findViewById(R.id.register_repassword_layout_id)

        nameText = view.findViewById(R.id.register_name_text_id)
        mobileText = view.findViewById(R.id.register_mobile_text_id)
        emailText = view.findViewById(R.id.register_email_text_id)
        passwordText = view.findViewById(R.id.register_password_text_id)
        rePasswordText = view.findViewById(R.id.register_repassword_text_id)

        courseSpinner = view.findViewById(R.id.register_course_spinner_id)
        streamSpinner = view.findViewById(R.id.register_stream_spinner_id)
        classSpinner = view.findViewById(R.id.register_class_spinner_id)

        registerButton.layoutParams.width = (AppUtils.SCREEN_WIDTH * 0.40).toInt()

        val fontGeneral = AppUtils.getTypeFace(mContext!!, "")
        registerTitle.typeface = AppUtils.getTypeFace(mContext!!, "title")

        nameTextLayout.typeface = fontGeneral
        mobileTextLayout.typeface = fontGeneral
        emailTextLayout.typeface = fontGeneral
        passwordTextLayout.typeface = fontGeneral
        rePasswordTextLayout.typeface = fontGeneral

        nameText.typeface = fontGeneral
        mobileText.typeface = fontGeneral
        emailText.typeface = fontGeneral
        passwordText.typeface = fontGeneral
        rePasswordText.typeface = fontGeneral

        loginText.typeface = fontGeneral
        registerButton.typeface = fontGeneral

        view.findViewById<TextView>(R.id.register_credentials_title_id).typeface = fontGeneral
        view.findViewById<TextView>(R.id.registrer_course_detail_title_id).typeface = fontGeneral

        view.findViewById<TextView>(R.id.register_course_title_id).typeface = fontGeneral
        view.findViewById<TextView>(R.id.register_stream_title_id).typeface = fontGeneral
        view.findViewById<TextView>(R.id.register_class_title_id).typeface = fontGeneral

        preferenceUtils = PreferenceUtils(mContext!!)

        usProgress = USProgressBar(mContext!!)
        usProgress.setDescriptionText(R.string.connecting)
        usProgress.setTitleText(R.string.loading)
        usProgress.setProgressStyle(AppUtils.ProgressStyle)

        loginText.setOnClickListener(this)
        registerButton.setOnClickListener(this)

        spinnerInit()
    }

    private fun courseListRetrofit() {
        if (AppUtils.networkConnectivityCheck(mContext)) {
            usProgress.showDialog()

            val apiCallRequest = APICallRequest(mContext)
            val courseListener = this as OnCourseListRetroListener
            apiCallRequest.getCourseList(courseListener)
        }
    }

    private fun specializationRetrofit(courseCode: String) {
        if (AppUtils.networkConnectivityCheck(mContext)) {
            usProgress.showDialog()

            val apiCallRequest = APICallRequest(mContext)
            val specListener = this as OnSpecializationRetroList
            apiCallRequest.getSpecializationList(courseCode, specListener)
        }
    }

    private fun spinnerInit() {
        streamList = ArrayList()
        courseList = ArrayList()

        courseList.add("Select Course")
        streamList.add("Select Specialization")

        classList = ArrayList()
        classList.add("Select Class")
        classList.add("First Year")
        classList.add("Second Year")
        classList.add("Third Year")
        classList.add("Fourth Year")
        classList.add("Fifth Year")

        val classAdapter =
            ArrayAdapter<String>(mContext!!, android.R.layout.simple_spinner_item, classList)
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        classSpinner.adapter = classAdapter
        classSpinner.onItemSelectedListener = SetOnItemSelectListener()

        streamAdapter =
            ArrayAdapter<String>(mContext!!, android.R.layout.simple_spinner_item, streamList)
        streamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        streamSpinner.adapter = streamAdapter
        streamSpinner.onItemSelectedListener = SetOnItemSelectListener()

        courseAdapter =
            ArrayAdapter<String>(mContext!!, android.R.layout.simple_spinner_item, courseList)
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        courseSpinner.adapter = courseAdapter
        courseSpinner.onItemSelectedListener = SetOnItemSelectListener()

        courseListRetrofit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_register_id -> {
                listener.onLoginFragmentListener()
            }

            R.id.register_register_btn_id -> {
                registrationVerification()
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

    private fun registrationVerification() {
        if (AppUtils.networkConnectivityCheck(mContext)) {
            val nameET = nameText.text.toString().trim()
            val mobileET = mobileText.text.toString().trim()
            val emailET = emailText.text.toString().trim()
            val pswdET = passwordText.text.toString().trim()
            val repswdET = rePasswordText.text.toString().trim()

            if (nameET.isNotEmpty() && mobileET.length == 10 && isValidEmailId(emailET)
                && pswdET.length >= 8 && pswdET == repswdET &&
                courseSelectPos > 0 && (courseSelectPos - 1) < courseModelList.size &&
                streamSelectPos > 0 && (streamSelectPos - 1) < streamModelList.size &&
                classSelectPos > 0) {
                usProgress.showDialog()

                val jsonObject = JSONObject()
                jsonObject.put("UserName", nameET)
                jsonObject.put("Contact", mobileET)
                jsonObject.put("Email", emailET)
                jsonObject.put("Password", pswdET)
                jsonObject.put("Course", courseModelList[courseSelectPos - 1].courseCode)
                jsonObject.put("Stream", streamModelList[streamSelectPos - 1].specCode)
                jsonObject.put("ClassCourse", classList[classSelectPos])
                jsonObject.put("AcountVerified", false)
                jsonObject.put("IsTeacher", false)
                jsonObject.put("DeviceCode", AppUtils.generateDeviceCode())

                val apiCallRequest = APICallRequest(mContext)
                val registrationListener = this as OnUserRegisterRetroListener
                apiCallRequest.registerUserToServer(
                    emailET,
                    jsonObject.toString(),
                    registrationListener
                )

            } else {
                if (nameET.isEmpty()) {
                    nameText.error = mContext?.getString(R.string.name_error)
                }
                if (mobileET.length != 10) {
                    mobileText.error = mContext?.getString(R.string.mobile_number_error)
                }
                if (!isValidEmailId(emailET)) {
                    emailText.error = mContext?.getString(R.string.email_error)
                }
                if (pswdET.length < 8) {
                    passwordText.setText("")
                    passwordText.error = mContext?.getString(R.string.pswd_error)
                }
                if (repswdET.length < 8) {
                    rePasswordText.setText("")
                    rePasswordText.error = mContext?.getString(R.string.pswd_error)
                }
                if (pswdET != repswdET) {
                    passwordText.setText("")
                    rePasswordText.setText("")
                    passwordText.error = mContext?.getString(R.string.pswd_mismatch_error)
                }
                if (courseSelectPos == 0) {
                    Toast.makeText(mContext, R.string.course_error, Toast.LENGTH_SHORT).show()
                }
                if (streamSelectPos == 0) {
                    Toast.makeText(mContext, R.string.stream_error, Toast.LENGTH_SHORT).show()
                }
                if (classSelectPos == 0) {
                    Toast.makeText(mContext, R.string.class_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    inner class SetOnItemSelectListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (parent?.id) {
                R.id.register_course_spinner_id -> {
                    courseSelectPos = parent.selectedItemPosition
                    if ((courseSelectPos - 1) >= 0 && (courseSelectPos - 1) < courseModelList.size && courseModelList.size > 0) {
                        specializationRetrofit(courseModelList[courseSelectPos - 1].courseCode)
                    }
                }

                R.id.register_stream_spinner_id -> {
                    streamSelectPos = parent.selectedItemPosition
                }

                R.id.register_class_spinner_id -> {
                    classSelectPos = parent.selectedItemPosition
                    //parent.selectedItem.toString()
                }
            }
        }
    }

    interface OnRegisterFragmentListener {
        fun onFragmentContextFailListener()
        fun onLoginFragmentListener()
        fun onSuccessfulRegisterListener()
    }

    override fun onFailureRegistration(message: String) {
        usProgress.dismissDialog()
        if (message.isEmpty()) {
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessfulRegistration(statusResponse: StatusResponse?) {
        usProgress.dismissDialog()
        if (statusResponse?.status!!) {
            listener.onSuccessfulRegisterListener()
        } else {
            AlertDialog.Builder(mContext)
                .setTitle("Failed!")
                .setMessage(statusResponse.message)
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                    listener.onLoginFragmentListener()
                }
                .create().show()
        }
    }

    override fun onFailedSpecListener(message: String) {
        usProgress.dismissDialog()
        if (message.isEmpty()) {
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessfulSpecListener(specializationModel: SpecializationModel?) {
        usProgress.dismissDialog()
        streamModelList = ArrayList()
        if (specializationModel != null) {
            streamList.clear()
            streamList.add("Select Specialization")
            streamModelList = specializationModel.specialization as ArrayList<Specialization>
            for (stream in streamModelList) {
                streamList.add(stream.specName)
            }
        }
        streamAdapter.notifyDataSetChanged()
    }

    override fun onFailedCourseListener(message: String) {
        usProgress.dismissDialog()
        if (message.isEmpty()) {
            Toast.makeText(mContext, R.string.server_error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessfulCourseListener(courseModel: CourseModel?) {
        usProgress.dismissDialog()
        courseModelList = ArrayList()
        if (courseModel != null) {
            courseList.clear()
            courseList.add("Select Course")
            courseModelList = courseModel.course as ArrayList<Course>
            for (course in courseModelList) {
                courseList.add(course.courseName)
            }
        }
        courseAdapter.notifyDataSetChanged()
    }
}