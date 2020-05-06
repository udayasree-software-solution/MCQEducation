package com.udayasreesoftwaresolution.mcqeducation.retrofits.retrointerface;

import com.udayasreesoftwaresolution.mcqeducation.retrofits.models.CourseModel;

import java.util.ArrayList;

public interface OnCourseListRetroListener {
    void onSuccessfulCourseListener(CourseModel courseList);
    void onFailedCourseListener(String message);
}
