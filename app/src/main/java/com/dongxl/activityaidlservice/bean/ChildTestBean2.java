package com.dongxl.activityaidlservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class ChildTestBean2 extends ChildTestBean implements Parcelable {
    private long long1;

    public long getLong1() {
        return long1;
    }

    public void setLong1(long long1) {
        this.long1 = long1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.long1);
    }

    public ChildTestBean2() {
    }

    protected ChildTestBean2(Parcel in) {
        super(in);
        this.long1 = in.readLong();
    }

    public static final Creator<ChildTestBean2> CREATOR = new Creator<ChildTestBean2>() {
        @Override
        public ChildTestBean2 createFromParcel(Parcel source) {
            return new ChildTestBean2(source);
        }

        @Override
        public ChildTestBean2[] newArray(int size) {
            return new ChildTestBean2[size];
        }
    };

    @Override
    public String toString() {
        return "ChildTestBean2{" +
                "long1=" + long1 +
                '}';
    }
}
