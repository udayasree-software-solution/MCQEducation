package com.udayasreesoftwaresolution.mcqeducation.retrofits.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecializationModel implements Parcelable {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("specialization")
    @Expose
    private List<Specialization> specialization = null;
    public final static Parcelable.Creator<SpecializationModel> CREATOR = new Creator<SpecializationModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SpecializationModel createFromParcel(Parcel in) {
            return new SpecializationModel(in);
        }

        public SpecializationModel[] newArray(int size) {
            return (new SpecializationModel[size]);
        }

    }
            ;

    protected SpecializationModel(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.specialization, (com.udayasreesoftwaresolution.mcqeducation.retrofits.models.Specialization.class.getClassLoader()));
    }

    public SpecializationModel() {
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

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(specialization);
    }

    public int describeContents() {
        return 0;
    }
}
