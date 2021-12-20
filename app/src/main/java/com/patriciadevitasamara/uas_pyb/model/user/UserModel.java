package com.patriciadevitasamara.uas_pyb.model.user;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("photo_profile")
    private Object photoProfile;

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("email_verified_at")
    private Object emailVerifiedAt;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("address")
    private String address;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    protected UserModel(Parcel in) {
        name = in.readString();
        updatedAt = in.readString();
        photoProfile = in.readString();
        createdAt = in.readString();
        emailVerifiedAt = in.readString();
        email = in.readString();
        id = in.readInt();
        username = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
    }


    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(Object photoProfile) {
        this.photoProfile = photoProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(updatedAt);
        parcel.writeString(String.valueOf(photoProfile));
        parcel.writeString(createdAt);
        parcel.writeString(String.valueOf(emailVerifiedAt));
        parcel.writeString(email);
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);

    }

    public UserModel(String name, String email, String username, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
//        this.photoProfile = photoProfile;
    }
}
