package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginModel implements Parcelable {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("Course")
    @Expose
    private String course;
    @SerializedName("Stream")
    @Expose
    private String stream;
    @SerializedName("ClassCourse")
    @Expose
    private String classCourse;
    @SerializedName("AcountVerified")
    @Expose
    private Boolean acountVerified;
    @SerializedName("IsTeacher")
    @Expose
    private Boolean isTeacher;
    @SerializedName("DeviceCode")
    @Expose
    private String deviceCode;
    public final static Parcelable.Creator<UserLoginModel> CREATOR = new Creator<UserLoginModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserLoginModel createFromParcel(Parcel in) {
            return new UserLoginModel(in);
        }

        public UserLoginModel[] newArray(int size) {
            return (new UserLoginModel[size]);
        }

    }
            ;

    protected UserLoginModel(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.contact = ((String) in.readValue((String.class.getClassLoader())));
        this.course = ((String) in.readValue((String.class.getClassLoader())));
        this.stream = ((String) in.readValue((String.class.getClassLoader())));
        this.classCourse = ((String) in.readValue((String.class.getClassLoader())));
        this.acountVerified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isTeacher = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.deviceCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserLoginModel() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getClassCourse() {
        return classCourse;
    }

    public void setClassCourse(String classCourse) {
        this.classCourse = classCourse;
    }

    public Boolean getAcountVerified() {
        return acountVerified;
    }

    public void setAcountVerified(Boolean acountVerified) {
        this.acountVerified = acountVerified;
    }

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(id);
        dest.writeValue(userName);
        dest.writeValue(email);
        dest.writeValue(contact);
        dest.writeValue(course);
        dest.writeValue(stream);
        dest.writeValue(classCourse);
        dest.writeValue(acountVerified);
        dest.writeValue(isTeacher);
        dest.writeValue(deviceCode);
    }

    public int describeContents() {
        return 0;
    }
}
