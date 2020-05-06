package com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.UserLoginModel;

public interface OnDeviceVerifyRetroListener {
    void onSuccessfulDeviceCode();
    void onFailureDeviceCode(String message);
}
