package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseModel implements Parcelable {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("course")
    @Expose
    private List<Course> course = null;
    public final static Parcelable.Creator<CourseModel> CREATOR = new Creator<CourseModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        public CourseModel[] newArray(int size) {
            return (new CourseModel[size]);
        }

    }
            ;

    protected CourseModel(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.course, (com.udayasreesoftwaresolution.mcqeducation.retrofits.models.Course.class.getClassLoader()));
    }

    public CourseModel() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(course);
    }

    public int describeContents() {
        return 0;
    }

}
