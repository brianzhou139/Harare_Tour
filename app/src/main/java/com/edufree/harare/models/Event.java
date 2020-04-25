package com.edufree.harare.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Event implements Parcelable {
    private String event_name;
    private String event_description;
    private String event_date;
    private String event_address;
    private int event_foto;


    public Event(){}

    public Event(String event_name, String event_description, String event_date, String event_address, int event_foto) {
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_date = event_date;
        this.event_address = event_address;
        this.event_foto = event_foto;
    }

    protected Event(Parcel in) {
        event_name = in.readString();
        event_description = in.readString();
        event_date = in.readString();
        event_address = in.readString();
        event_foto = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }


    public int getEvent_foto() {
        return event_foto;
    }

    public void setEvent_foto(int event_foto) {
        this.event_foto = event_foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(event_name);
        parcel.writeString(event_description);
        parcel.writeString(event_date);
        parcel.writeString(event_address);
        parcel.writeInt(event_foto);
    }
}
