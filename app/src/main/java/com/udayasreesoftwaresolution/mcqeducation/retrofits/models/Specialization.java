package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialization  implements Parcelable {
    @SerializedName("CourseCode")
    @Expose
    private String courseCode;
    @SerializedName("SpecCode")
    @Expose
    private String specCode;
    @SerializedName("SpecName")
    @Expose
    private String specName;
    public final static Parcelable.Creator<Specialization> CREATOR = new Creator<Specialization>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Specialization createFromParcel(Parcel in) {
            return new Specialization(in);
        }

        public Specialization[] newArray(int size) {
            return (new Specialization[size]);
        }

    }
            ;

    protected Specialization(Parcel in) {
        this.courseCode = ((String) in.readValue((String.class.getClassLoader())));
        this.specCode = ((String) in.readValue((String.class.getClassLoader())));
        this.specName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Specialization() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(courseCode);
        dest.writeValue(specCode);
        dest.writeValue(specName);
    }

    public int describeContents() {
        return 0;
    }
}
