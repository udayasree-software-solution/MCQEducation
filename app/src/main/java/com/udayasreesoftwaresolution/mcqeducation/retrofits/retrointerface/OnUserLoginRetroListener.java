package com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel;

public interface OnUserLoginRetroListener {
    void onSuccessfulLoginDetail();
    void onFailureLoginDetail(String message);
}
