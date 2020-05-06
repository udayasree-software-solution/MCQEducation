package com.udayasreesoftwaresolution.mcqeducation.retrofits;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.CourseModel;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.SpecializationModel;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.StatusResponse;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("/android/MCQEducation/webservice/UserLoginAction.php")
    Call<UserLoginModel> getUserLoginDetails(
            @Field("security_key") String securityKey,
            @Field("email_id") String emailId,
            @Field("password") String password,
            @Field("version") String version,
            @Field("action_type") String actionType
    );

    @FormUrlEncoded
    @POST("/android/MCQEducation/webservice/UserLoginAction.php")
    Call<StatusResponse> userRegistrationForm(
            @Field("security_key") String securityKey,
            @Field("email_id") String emailId,
            @Field("user_detail") String userDetail,
            @Field("version") String version,
            @Field("action_type") String actionType
    );

    @FormUrlEncoded
    @POST("/android/MCQEducation/webservice/UserLoginAction.php")
    Call<UserLoginModel> verifyDeviceCodeForm(
            @Field("security_key") String securityKey,
            @Field("email_id") String emailId,
            @Field("course") String course,
            @Field("stream") String stream,
            @Field("class_course") String classCourse,
            @Field("device_code") String device_code,
            @Field("version") String version,
            @Field("action_type") String actionType
    );

    @FormUrlEncoded
    @POST("/android/MCQEducation/webservice/CourseSpecializationAction.php")
    Call<CourseModel> getCourseListFromServer(
            @Field("security_key") String securityKey,
            @Field("action_type") String actionType
    );

    @FormUrlEncoded
    @POST("/android/MCQEducation/webservice/CourseSpecializationAction.php")
    Call<SpecializationModel> getSpecListFromServer(
            @Field("security_key") String securityKey,
            @Field("course_code") String courseCode,
            @Field("action_type") String actionType
    );
}
