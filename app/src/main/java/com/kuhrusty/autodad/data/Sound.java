package com.kuhrusty.autodad.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rusty on 1/6/15.
 */
public class Sound implements Parcelable {
    private final static int UNKNOWN_RESOURCE_ID = 0;  //  we hope that's not used!

    private int resourceID = UNKNOWN_RESOURCE_ID;
    private String name;

    public Sound() {
    }
    public Sound(int resourceID, String name) {
        this.resourceID = resourceID;
        this.name = name;
    }

    public int getResourceID() {
        return resourceID;
    }
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * Returns true if getResourceID() isn't our unknown resource ID, which...
     * is not a very good check.
     */
    public boolean isValid() {
        return resourceID != UNKNOWN_RESOURCE_ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //  Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    //  Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resourceID);
        dest.writeString(name);
    }

    public static final Creator<Sound> CREATOR = new Creator<Sound>() {
        public Sound createFromParcel(Parcel in) {
            int resID = in.readInt();
            String name = in.readString();
            return new Sound(resID, name);
        }
        public Sound[] newArray(int size) {
            return new Sound[size];
        }
    };
}
