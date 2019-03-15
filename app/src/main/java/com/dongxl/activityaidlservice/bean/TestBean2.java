package com.dongxl.activityaidlservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class TestBean2 extends TestBean implements Parcelable {
    private double aDouble1;

    public double getaDouble1() {
        return aDouble1;
    }

    public void setaDouble1(double aDouble1) {
        this.aDouble1 = aDouble1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(this.aDouble1);
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        this.aDouble1 = in.readDouble();
    }

    public TestBean2() {
    }

    protected TestBean2(Parcel in) {
        super(in);
        this.aDouble1 = in.readDouble();
    }

    public static final Creator<TestBean2> CREATOR = new Creator<TestBean2>() {
        @Override
        public TestBean2 createFromParcel(Parcel source) {
            return new TestBean2(source);
        }

        @Override
        public TestBean2[] newArray(int size) {
            return new TestBean2[size];
        }
    };

    @Override
    public String toString() {
        return "TestBean2{" +
                "aDouble1=" + aDouble1 +
                '}';
    }
}
