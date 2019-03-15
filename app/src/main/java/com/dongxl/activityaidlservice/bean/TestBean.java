package com.dongxl.activityaidlservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class TestBean implements Parcelable {
    private String testStr1;
    private ChildTestBean childTestBean;
    private List<ChildTestBean> list1;
    private Map<String, ChildTestBean> map1;

    public ChildTestBean getChildTestBean() {
        return childTestBean;
    }

    public void setChildTestBean(ChildTestBean childTestBean) {
        this.childTestBean = childTestBean;
    }

    public List<ChildTestBean> getList1() {
        return list1;
    }

    public void setList1(List<ChildTestBean> list1) {
        this.list1 = list1;
    }

    public Map<String, ChildTestBean> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, ChildTestBean> map1) {
        this.map1 = map1;
    }

    public String getTestStr1() {
        return testStr1;
    }

    public void setTestStr1(String testStr1) {
        this.testStr1 = testStr1;
    }

    public TestBean() {
    }

    public void readFromParcel(Parcel in) {
        this.testStr1 = in.readString();
        this.childTestBean = in.readParcelable(ChildTestBean.class.getClassLoader());
        this.list1 = in.createTypedArrayList(ChildTestBean.CREATOR);
        int map1Size = in.readInt();
        this.map1 = new HashMap<String, ChildTestBean>(map1Size);
        for (int i = 0; i < map1Size; i++) {
            String key = in.readString();
            ChildTestBean value = in.readParcelable(ChildTestBean.class.getClassLoader());
            this.map1.put(key, value);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.testStr1);
        dest.writeParcelable(this.childTestBean, flags);
        dest.writeTypedList(this.list1);
        dest.writeInt(this.map1.size());
        for (Map.Entry<String, ChildTestBean> entry : this.map1.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    protected TestBean(Parcel in) {
        this.testStr1 = in.readString();
        this.childTestBean = in.readParcelable(ChildTestBean.class.getClassLoader());
        this.list1 = in.createTypedArrayList(ChildTestBean.CREATOR);
        int map1Size = in.readInt();
        this.map1 = new HashMap<String, ChildTestBean>(map1Size);
        for (int i = 0; i < map1Size; i++) {
            String key = in.readString();
            ChildTestBean value = in.readParcelable(ChildTestBean.class.getClassLoader());
            this.map1.put(key, value);
        }
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };

    @Override
    public String toString() {
        return "TestBean{" +
                "testStr1='" + testStr1 + '\'' +
                ", childTestBean=" + childTestBean +
                ", list1=" + list1 +
                ", map1=" + map1 +
                '}';
    }
}
