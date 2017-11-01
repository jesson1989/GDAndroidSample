package com.asus.netusage.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jesson_Yi on 2017/10/31.
 */

public class NetUsageInfo implements Parcelable {

    public int  isSetLimit;
    public long reminder;
    public long todayUsed;
    public long usageLimit;

    public NetUsageInfo(int isSetLimit, long reminder, long todayUsed,long usageLimit) {
        this.isSetLimit = isSetLimit;
        this.reminder = reminder;
        this.todayUsed = todayUsed;
        this.usageLimit = usageLimit;
    }

    protected NetUsageInfo(Parcel in) {
        this.isSetLimit = in.readInt();
        this.reminder = in.readLong();
        this.todayUsed = in.readLong();
        this.usageLimit = in.readLong();
    }

    public static final Creator<NetUsageInfo> CREATOR = new Creator<NetUsageInfo>() {
        @Override
        public NetUsageInfo createFromParcel(Parcel in) {
            return new NetUsageInfo(in);
        }

        @Override
        public NetUsageInfo[] newArray(int size) {
            return new NetUsageInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(isSetLimit);
        parcel.writeLong(reminder);
        parcel.writeLong(todayUsed);
        parcel.writeLong(usageLimit);
    }

    @Override
    public String toString() {
        return "NetUsageInfo{" +
                "isSetLimit=" + isSetLimit+
                ", reminder='" + reminder + '\'' +
                ", todayUsed='" + todayUsed + '\''+
                ", useLimit='" + usageLimit + '\''+
                '}';
    }
}