package com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.StatusResponse;

public interface OnUserRegisterRetroListener {
    void onSuccessfulRegistration(StatusResponse statusResponse);
    void onFailureRegistration(String message);
}
