package com.edufree.harare.models;

import android.os.Parcel;
import android.os.Parcelable;

public class tourGuide implements Parcelable {
    private String tourName;
    private String tourCategory;
    private String tourDetails;
    private String tourAddress;
    private int tourImage=0;
    private String tourPhone;
    private String tourImageUrl=null;

    public tourGuide(){}

    public tourGuide(String tourName, String tourCategory, String tourDetails, String tourAddress, int tourImage) {
        this.tourName = tourName;
        this.tourCategory = tourCategory;
        this.tourDetails = tourDetails;
        this.tourAddress = tourAddress;
        this.tourImage = tourImage;
    }

    protected tourGuide(Parcel in) {
        tourName = in.readString();
        tourCategory = in.readString();
        tourDetails = in.readString();
        tourAddress = in.readString();
        tourImage = in.readInt();
        tourPhone = in.readString();
        tourImageUrl = in.readString();
    }

    public static final Creator<tourGuide> CREATOR = new Creator<tourGuide>() {
        @Override
        public tourGuide createFromParcel(Parcel in) {
            return new tourGuide(in);
        }

        @Override
        public tourGuide[] newArray(int size) {
            return new tourGuide[size];
        }
    };

    public String getTourImageUrl() {
        return tourImageUrl;
    }

    public void setTourImageUrl(String tourImageUrl) {
        this.tourImageUrl = tourImageUrl;
    }

    public String getTourPhone() {
        return tourPhone;
    }

    public void setTourPhone(String tourPhone) {
        this.tourPhone = tourPhone;
    }

    public String getTourAddress() {
        return tourAddress;
    }

    public void setTourAddress(String tourAddress) {
        this.tourAddress = tourAddress;
    }

    public int getTourImage() {
        return tourImage;
    }

    public void setTourImage(int tourImage) {
        this.tourImage = tourImage;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourCategory() {
        return tourCategory;
    }

    public void setTourCategory(String tourCategory) {
        this.tourCategory = tourCategory;
    }

    public String getTourDetails() {
        return tourDetails;
    }

    public void setTourDetails(String tourDetails) {
        this.tourDetails = tourDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tourName);
        parcel.writeString(tourCategory);
        parcel.writeString(tourDetails);
        parcel.writeString(tourAddress);
        parcel.writeInt(tourImage);
        parcel.writeString(tourPhone);
        parcel.writeString(tourImageUrl);
    }
}
