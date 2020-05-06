package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<StatusResponse> CREATOR = new Creator<StatusResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatusResponse createFromParcel(Parcel in) {
            return new StatusResponse(in);
        }

        public StatusResponse[] newArray(int size) {
            return (new StatusResponse[size]);
        }

    }
            ;

    protected StatusResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StatusResponse() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }
}
