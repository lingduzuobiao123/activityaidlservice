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
public class ChildTestBean implements Parcelable {
    private String string;
    private List<String> list1;
    private Map<String, Integer> map1;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public Map<String, Integer> getMap1() {
        return map1;
    }

    public void setMap1(Map<String, Integer> map1) {
        this.map1 = map1;
    }

    public ChildTestBean() {
    }

    @Override
    public String toString() {
        return "ChildTestBean{" +
                "string='" + string + '\'' +
                ", list1=" + list1 +
                ", map1=" + map1 +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.string);
        dest.writeStringList(this.list1);
        dest.writeInt(this.map1.size());
        for (Map.Entry<String, Integer> entry : this.map1.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
    }

    protected ChildTestBean(Parcel in) {
        this.string = in.readString();
        this.list1 = in.createStringArrayList();
        int map1Size = in.readInt();
        this.map1 = new HashMap<String, Integer>(map1Size);
        for (int i = 0; i < map1Size; i++) {
            String key = in.readString();
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.map1.put(key, value);
        }
    }

    public static final Creator<ChildTestBean> CREATOR = new Creator<ChildTestBean>() {
        @Override
        public ChildTestBean createFromParcel(Parcel source) {
            return new ChildTestBean(source);
        }

        @Override
        public ChildTestBean[] newArray(int size) {
            return new ChildTestBean[size];
        }
    };
}
