package com.edufree.harare.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Guide implements Parcelable {
    private String guide_name;
    private int guide_foto;

    public Guide(){}

    public Guide(String guide_name, int guide_foto) {
        this.guide_name = guide_name;
        this.guide_foto = guide_foto;
    }

    protected Guide(Parcel in) {
        guide_name = in.readString();
        guide_foto = in.readInt();
    }

    public static final Creator<Guide> CREATOR = new Creator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel in) {
            return new Guide(in);
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[size];
        }
    };

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public int getGuide_foto() {
        return guide_foto;
    }

    public void setGuide_foto(int guide_foto) {
        this.guide_foto = guide_foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guide_name);
        parcel.writeInt(guide_foto);
    }
}
