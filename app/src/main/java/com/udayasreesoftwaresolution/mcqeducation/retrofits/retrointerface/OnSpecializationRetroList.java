package com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.SpecializationModel;

import java.util.ArrayList;

public interface OnSpecializationRetroList {
    void onSuccessfulSpecListener(SpecializationModel specializationList);
    void onFailedSpecListener(String message);
}
