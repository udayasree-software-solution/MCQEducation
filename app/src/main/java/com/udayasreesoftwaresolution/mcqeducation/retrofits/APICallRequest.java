package com.udayasreesoftwaresolution.mcqeducation.retrofits;

import android.content.Context;


import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.CourseModel;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.SpecializationModel;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.StatusResponse;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnCourseListRetroListener;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnDeviceVerifyRetroListener;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnSpecializationRetroList;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnUserLoginRetroListener;
import com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface.OnUserRegisterRetroListener;
import com.udayasreesoftwaresolution.mcqeducation.utils.PreferenceUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICallRequest {
    private Context mContext;
    private PreferenceUtils preferenceUtils = null;

    public APICallRequest(Context mContext) {
        this.mContext = mContext;
        if (preferenceUtils == null) {
            preferenceUtils = new PreferenceUtils(mContext);
        }
    }

    public void registerUserToServer(String emailId, String userDetail, OnUserRegisterRetroListener listener) {
        if (preferenceUtils != null) {
            APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
            Call<StatusResponse> call = apiInterface.userRegistrationForm (
                    preferenceUtils.getSecurityKey(),
                    emailId,
                    userDetail,
                    preferenceUtils.getAppVersion(),
                    "createuserlogin"
            );
            call.enqueue(new Callback<StatusResponse>() {
                @Override
                public void onResponse(@NotNull Call<StatusResponse> call, @NotNull Response<StatusResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getStatus()) {
                        listener.onSuccessfulRegistration(response.body());
                    } else {
                        String msg = (response.body() != null && !response.body().getStatus()) ? response.body().getMessage(): "";
                        listener.onFailureRegistration(msg);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<StatusResponse> call, @NotNull Throwable t) {
                    listener.onFailureRegistration("");
                }
            });
        }
    }

    public void getUserDetailFromServer(String emailId, String password, OnUserLoginRetroListener listener) {
        if (preferenceUtils != null) {
            APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
            Call<UserLoginModel> call = apiInterface.getUserLoginDetails (
                    preferenceUtils.getSecurityKey(),
                    emailId,
                    password,
                    preferenceUtils.getAppVersion(),
                    "getuserlogin"
            );
            call.enqueue(new Callback<UserLoginModel>() {
                @Override
                public void onResponse(@NotNull Call<UserLoginModel> call, @NotNull Response<UserLoginModel> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getStatus()) {
                        preferenceUtils.clearPreference();
                        UserLoginModel model = response.body();
                        preferenceUtils.setUserName(model.getUserName());
                        preferenceUtils.setUserEmail(model.getEmail());
                        preferenceUtils.setUserMobile(model.getContact());
                        preferenceUtils.setCourseCode(model.getCourse());
                        preferenceUtils.setStreamCode(model.getStream());
                        preferenceUtils.setClassCode(model.getClassCourse());
                        preferenceUtils.setIsTeacher(model.getIsTeacher());
                        preferenceUtils.setIsAccountVerify(model.getAcountVerified());
                        preferenceUtils.setDeviceCode(model.getDeviceCode());
                        preferenceUtils.setOneTimeLogin(true);

                        listener.onSuccessfulLoginDetail();
                    } else {
                        String msg = (response.body() != null && !response.body().getStatus()) ? response.body().getMessage(): "";
                        listener.onFailureLoginDetail(msg);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<UserLoginModel> call, @NotNull Throwable t) {
                    listener.onFailureLoginDetail("");
                }
            });
        }
    }

    public void verifyDeviceCodeFromServer(String emailId, String course, String stream, String classYear, String deviceCode,
                                           OnDeviceVerifyRetroListener listener) {
        if (preferenceUtils != null) {
            APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
            Call<UserLoginModel> call = apiInterface.verifyDeviceCodeForm (
                    preferenceUtils.getSecurityKey(),
                    emailId,
                    course,
                    stream,
                    classYear,
                    deviceCode,
                    preferenceUtils.getAppVersion(),
                    "verifydevicecode"
            );
            call.enqueue(new Callback<UserLoginModel>() {
                @Override
                public void onResponse(@NotNull Call<UserLoginModel> call, @NotNull Response<UserLoginModel> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getStatus()) {
                        UserLoginModel model = response.body();
                        preferenceUtils.setUserName(model.getUserName());
                        preferenceUtils.setUserEmail(model.getEmail());
                        preferenceUtils.setUserMobile(model.getContact());
                        preferenceUtils.setCourseCode(model.getCourse());
                        preferenceUtils.setStreamCode(model.getStream());
                        preferenceUtils.setClassCode(model.getClassCourse());
                        preferenceUtils.setIsTeacher(model.getIsTeacher());
                        preferenceUtils.setIsAccountVerify(model.getAcountVerified());
                        preferenceUtils.setDeviceCode(model.getDeviceCode());
                        preferenceUtils.setOneTimeLogin(true);

                        listener.onSuccessfulDeviceCode();
                    } else {
                        String msg = (response.body() != null && !response.body().getStatus()) ? response.body().getMessage(): "";
                        listener.onFailureDeviceCode(msg);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<UserLoginModel> call, @NotNull Throwable t) {
                    listener.onFailureDeviceCode("");
                }
            });
        }
    }

    public void getCourseList(OnCourseListRetroListener listener) {
        if (preferenceUtils != null) {
            APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
            Call<CourseModel> call = apiInterface.getCourseListFromServer (
                    preferenceUtils.getSecurityKey(),
                    "getcourselist"
            );
            call.enqueue(new Callback<CourseModel>() {
                @Override
                public void onResponse(@NotNull Call<CourseModel> call, @NotNull Response<CourseModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        listener.onSuccessfulCourseListener(response.body());
                    } else {
                        String msg = (response.body() != null && !response.body().getStatus()) ? response.body().getMessage(): "";
                        listener.onFailedCourseListener(msg);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<CourseModel> call, @NotNull Throwable t) {
                    listener.onFailedCourseListener("");
                }
            });
        }
    }

    public void getSpecializationList(String courseCode, OnSpecializationRetroList listener) {
        if (preferenceUtils != null) {
            APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);
            Call<SpecializationModel> call = apiInterface.getSpecListFromServer (
                    preferenceUtils.getSecurityKey(),
                    courseCode,
                    "getspecializationlist"
            );
            call.enqueue(new Callback<SpecializationModel>() {
                @Override
                public void onResponse(@NotNull Call<SpecializationModel> call, @NotNull Response<SpecializationModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        listener.onSuccessfulSpecListener(response.body());
                    } else {
                        String msg = (response.body() != null && !response.body().getStatus()) ? response.body().getMessage(): "";
                        listener.onFailedSpecListener(msg);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<SpecializationModel> call, @NotNull Throwable t) {
                    listener.onFailedSpecListener("");
                }
            });
        }
    }
}
