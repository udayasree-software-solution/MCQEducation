package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course implements Parcelable {
    @SerializedName("CourseCode")
    @Expose
    private String courseCode;
    @SerializedName("CourseName")
    @Expose
    private String courseName;
    public final static Parcelable.Creator<Course> CREATOR = new Creator<Course>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return (new Course[size]);
        }

    }
            ;

    protected Course(Parcel in) {
        this.courseCode = ((String) in.readValue((String.class.getClassLoader())));
        this.courseName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(courseCode);
        dest.writeValue(courseName);
    }

    public int describeContents() {
        return 0;
    }
}
